/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package circuit_touristique.controllers;

import circuit_touristique.entities.Circuit;
import circuit_touristique.services.EtapeService;
import circuit_touristique.services.ServiceCircuit;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


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

    public void setCircuit(Circuit circuit) {
        System.out.println("Set Circuit");
        this.circuit = circuit;
        circuit_nom.setText(circuit.getNom());
        circuit_description.setText(circuit.getDescription());
        Image image = new Image(circuit.getImageSrc());
        circuit_img.setImage(image);
        int columns = 0;
        int rows = 1;
        EtapeService service = new EtapeService();
        try {
            for (int i = 1; i < circuit.getDuree(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/planning.fxml"));
                VBox planningBox = fxmlLoader.load();

                PlanningController circuitController = fxmlLoader.getController();
                circuitController.setData(service.getPlanningJour(i, service.getTestData()));
                if (columns == 1) {
                    columns = 0;
                    ++rows;

                }
                planningGrid.add(planningBox, columns++, rows);
                //GridPane.setMargin(planningBox, new Insets(10));
            }
        } catch (IOException ex) {
            Logger.getLogger(CircuitDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
