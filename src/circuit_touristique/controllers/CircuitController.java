/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package circuit_touristique.controllers;

import circuit_touristique.entities.Circuit;
import circuit_touristique.utils.CircuitListener;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class CircuitController implements Initializable {

    @FXML
    private ImageView circuitImage;
    @FXML
    private Label circuitNom;
    @FXML
    private Label circuitDescription;
    @FXML
    private Label circuitPrix;

    @FXML
    private void click(MouseEvent mouseEvent) {
        System.out.println("Clicked: " + mouseEvent);
        System.out.println("Circuit: " + circuit);

        listener.onClickListener(circuit);
    }

    private CircuitListener listener;
    private Circuit circuit;

    public void setData(Circuit circuit, CircuitListener listener) {
        this.circuit = circuit;
        this.listener = listener;
        Image image = new Image(circuit.getImageSrc());
        circuitImage.setImage(image);
        circuitNom.setText(circuit.getNom());
        circuitDescription.setText(circuit.getDescription());
        circuitPrix.setText(circuit.getPrix() + " DNT");

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
