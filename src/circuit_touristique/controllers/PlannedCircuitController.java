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
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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
        this.circuit=circuit;
       this.date_debut_circuit=date_debut_circuit;
       this.nbr_personnes=nbr_personnes;
        
        selected_planned_circuit.setText(circuit.getNom());
        citrcuit_total_price.setText(circuit.getPrix()*nbr_personnes + " DNT");

    }

    @FXML
    private void reserver_circuit(ActionEvent event) {
       
       ReservationCircuit reservation= new ReservationCircuit(156,  circuit.getNc(),  date_debut_circuit,  nbr_personnes);
       ReservationService reservationService=new ReservationService();
       reservationService.ajouter(reservation);
       PlanningService planningService=new PlanningService();
       int nouvelleCapacite= planningService.getCapacity(circuit.getNc(),date_debut_circuit)- nbr_personnes;
       PlanningCircuit planning= new PlanningCircuit(circuit.getNc(),date_debut_circuit, nouvelleCapacite);
       planningService.modifierCapacite(planning);
       
       
       
        
    }

}
