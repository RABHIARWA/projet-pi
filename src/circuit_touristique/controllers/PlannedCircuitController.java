/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package circuit_touristique.controllers;

import circuit_touristique.utils.PlannedCircuit;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PlannedCircuitController {

    @FXML
    private Label selected_planned_circuit;
    @FXML
    private Label citrcuit_total_price;
    @FXML
    private Button reserver_circuit_button;

    public void setCircuitReservation(PlannedCircuit planned_circuit) {
        selected_planned_circuit.setText(planned_circuit.getCircuit_name());
        citrcuit_total_price.setText(planned_circuit.getPrice_total() + " DNT");

    }

}
