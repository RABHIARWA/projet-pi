/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package circuit_touristique.controllers;

import circuit_touristique.entities.Circuit;
import circuit_touristique.entities.PlanningCircuit;
import circuit_touristique.entities.ReservationCircuit;
import circuit_touristique.services.PlanningService;
import circuit_touristique.services.ReservationService;
import circuit_touristique.utils.PlannedCircuit;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PlannedCircuitController {

    private int nbr_personnes;
    private Date date_debut_circuit;
    private Circuit circuit;
    @FXML
    private Label selected_planned_circuit;
    @FXML
    private Label citrcuit_total_price;
    @FXML
    private Button reserver_circuit_button;

    public void setCircuitReservation(Circuit circuit, int nbr_personnes, Date date_debut_circuit) {
        this.circuit = circuit;
        this.date_debut_circuit = date_debut_circuit;
        this.nbr_personnes = nbr_personnes;

        selected_planned_circuit.setText(circuit.getNom());
        citrcuit_total_price.setText(circuit.getPrix() * nbr_personnes + " DNT");

    }

    @FXML
    private void reserver_circuit(ActionEvent event) {

        try {
            
            FXMLLoader reservationLoader = new FXMLLoader(getClass().getResource("/gui/reservation.fxml"));
            
            Parent reservationRoot = reservationLoader.load();
            ReservationController controller = reservationLoader.getController();
            //System.out.println(controller.toString());
            //controller.setCircuit(circuit);
             controller.setCircuitReservation(circuit, nbr_personnes, date_debut_circuit);
            //controller.mainPane.setCenter(circuitDetailRoot);
            //commenting that since it was not able to open new view on borderpane center
            //open in a new window instead
            Stage stage = new Stage();
            stage.setTitle("Roadrevel");
            stage.setScene(new Scene(reservationRoot, 1300, 900));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(PlannedCircuitController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
