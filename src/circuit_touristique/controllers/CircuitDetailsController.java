/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package circuit_touristique.controllers;

import circuit_touristique.entities.Circuit;
import circuit_touristique.entities.Ville;
import circuit_touristique.services.EtapeService;
import circuit_touristique.services.PlanningService;
import circuit_touristique.services.ServiceCircuit;
import circuit_touristique.services.VilleService;
import circuit_touristique.utils.PlannedCircuit;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class CircuitDetailsController {

    @FXML
    private Label circuit_nom;
    @FXML
    private Label circuit_description;
    @FXML
    private ImageView circuit_img;
    @FXML
    private GridPane planningGrid;

    private Circuit circuit;
    @FXML
    private HBox map_container;
    @FXML
    private DatePicker date_circuit;
    @FXML
    private Spinner<Integer> requested_number;
    @FXML
    private Button availability_button;
    @FXML
    private VBox planned_circuit_view;
    @FXML
    private WebView itinerary_view;

    public void setCircuit(Circuit circuit) {
        System.out.println("Set Circuit");
        this.circuit = circuit;
        circuit_nom.setText(circuit.getNom());
        circuit_description.setText(circuit.getDescription());
        Image image = new Image(circuit.getImageSrc());
        circuit_img.setImage(image);
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 2);
        requested_number.setValueFactory(valueFactory);
        date_circuit.setValue(LocalDate.now());
        planned_circuit_view.setVisible(false);
        planned_circuit_view.setManaged(false);
        EtapeService service = new EtapeService();
        WebEngine webEngine = itinerary_view.getEngine();
        webEngine.load("https://www.google.com/maps");
        VilleService ville_service = new VilleService();
// Wait for the page to finish loading
        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("load map");
            if (newValue == Worker.State.SUCCEEDED) {
                System.out.println("loading done");

                List<String> villes_circuit = service.getCircuitVilles(circuit.getNc());
                for (int i = 0; i < villes_circuit.size(); i++) {
                    Ville ville = new Ville(villes_circuit.get(i));
                    webEngine.executeScript("var map = new google.maps.Map(document.getElementById('map'), {zoom: 8, center: {lat: 51.5074, lng: -0.1278}});\n"
                            + "var marker = new google.maps.Marker({position: {lat: " + ville_service.setVilleData(ville).getLatitude() + ", lng: " + ville_service.setVilleData(ville).getLongitude() + "}, map: map, title: " + ville.getNom() + "});");
                }
            }
        });

        int columns = 0;
        int rows = 1;

        try {
            for (int i = 1; i <= circuit.getDuree(); i++) {
                System.out.println("ajouter le planning du jour" + i);

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/planning.fxml"));
                VBox planningBox = fxmlLoader.load();

                PlanningController circuitController = fxmlLoader.getController();
                circuitController.setData(service.getPlanningJour(i, service.getStepsForCircuit(circuit.getNc())));
                System.out.println("ajouter le planning du jour" + i);
                if (columns == 1) {
                    System.out.println("ajouter le planning du jour" + i);
                    columns = 0;
                    System.out.println("ajouter le planning du jour" + i);
                    ++rows;

                }
                planningGrid.add(planningBox, columns++, rows);
                //GridPane.setMargin(planningBox, new Insets(10));
            }
        } catch (IOException ex) {
            Logger.getLogger(CircuitDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void check_availibility(ActionEvent event) {
        planned_circuit_view.setVisible(false);
        planned_circuit_view.setManaged(false);
        planned_circuit_view.getChildren().clear();

        //boolean isCircuitAvailable(Date date, int capacite) ;
        PlanningService service = new PlanningService();
        boolean isCircuitAvailable = service.isCircuitAvailable(Date.valueOf(date_circuit.getValue()), requested_number.getValue());
        System.out.println("is available:" + isCircuitAvailable);
        if (isCircuitAvailable) {
            //planned_circuit
            try {
                //root = FXMLLoader.load(getClass().getResource("/gui/planned_circuit.fxml"));
                //planned_circuit_view.getChildren().add(root);

                FXMLLoader plannedCircuitLoader = new FXMLLoader(getClass().getResource("/gui/planned_circuit.fxml"));

                Parent plannedCircuitRoot = plannedCircuitLoader.load();

                PlannedCircuitController controller = plannedCircuitLoader.getController();
                controller.setCircuitReservation(circuit, requested_number.getValue(), Date.valueOf(date_circuit.getValue()));
                planned_circuit_view.getChildren().add(plannedCircuitRoot);

            } catch (IOException ex) {
                Logger.getLogger(CircuitDetailsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            planned_circuit_view.getChildren().add(new Label("Aucun circuit n'est planifié à cette date. \n Veuillez choisir d'autres dates!"));

        }
        planned_circuit_view.setVisible(true);
        planned_circuit_view.setManaged(true);

    }

}
