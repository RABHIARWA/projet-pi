/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package circuit_touristique.controllers;

import circuit_touristique.entities.Circuit;
import circuit_touristique.services.EtapeService;
import circuit_touristique.services.PlanningService;
import circuit_touristique.services.ServiceCircuit;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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

    public void setCircuit(Circuit circuit) {
        System.out.println("Set Circuit");
        this.circuit = circuit;
        circuit_nom.setText(circuit.getNom());
        circuit_description.setText(circuit.getDescription());
        Image image = new Image(circuit.getImageSrc());
        circuit_img.setImage(image);
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 2);
        requested_number.setValueFactory(valueFactory);
        planned_circuit_view.setVisible(false);
        planned_circuit_view.setManaged(false);

        //WebEngine webEngine = itinerary_view.getEngine();
        //webEngine.load("https://www.google.com/maps");
        int columns = 0;
        int rows = 1;
        EtapeService service = new EtapeService();
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

        //boolean isCircuitAvailable(Date date, int capacite) ;
        PlanningService service = new PlanningService();
        boolean isCircuitAvailable = service.isCircuitAvailable(Date.valueOf(date_circuit.getValue()), requested_number.getValue());
        System.out.println("is available:" + isCircuitAvailable);
        if (isCircuitAvailable) {
            planned_circuit_view.getChildren().add(new Label("Le circuit existe"));
        } else {
            planned_circuit_view.getChildren().add(new Label("Aucun circuit n'est planifié à cette date. \n Veuillez choisir d'autres dates!"));

        }
        planned_circuit_view.setVisible(true);
        planned_circuit_view.setManaged(true);
        
    }

}
