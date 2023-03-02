/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package circuit_touristique.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;


public class EntryController implements Initializable {

    @FXML
    public BorderPane mainPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/circuit_details.fxml"));

            mainPane.setCenter(loader.load());
        } catch (IOException ex) {
            Logger.getLogger(EntryController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
