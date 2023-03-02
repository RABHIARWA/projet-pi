/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package circuit_touristique.controllers;

import circuit_touristique.utils.PlanningJour;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 * 
 *
 */
public class PlanningController implements Initializable {

    @FXML
    private Label planningJourLabel;
    @FXML
    private Label planningNameLabel;
    @FXML
    private Label planningDescriptionLabel;

    private PlanningJour planningJour;

    public void setData(PlanningJour planningJour) {
        this.planningJour = planningJour;
        planningJourLabel.setText("Jour " + planningJour.getJour() + ":");
        planningNameLabel.setText(String.join(", ", planningJour.getVilles()));
        planningDescriptionLabel.setText(planningJour.getDescription());

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
