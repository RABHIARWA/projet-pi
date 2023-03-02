/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package circuit_touristique.controllers;

import circuit_touristique.entities.Circuit;
import circuit_touristique.services.ServiceCircuit;
import circuit_touristique.utils.CircuitListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class CircuitsListController implements Initializable {

    @FXML
    private GridPane circuitGrid;

    private List<Circuit> circuits;
    private CircuitListener circuitListener;

    private List<Circuit> data() {
        ServiceCircuit serviceCircuit = new ServiceCircuit();
        return serviceCircuit.getTestData();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        circuits = new ArrayList<>(data());

        circuitListener = new CircuitListener() {
            @Override
            public void onClickListener(Circuit circuit) {
                System.out.println("Clicked: " + circuit.getNc());
                try {
                    FXMLLoader circuitDetailsLoader = new FXMLLoader(getClass().getResource("/gui/circuit_details.fxml"));
                    //FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("/gui/entry.fxml"));
                    //mainLoader.load();
                    Parent circuitDetailRoot = circuitDetailsLoader.load();

                    CircuitDetailsController controller = circuitDetailsLoader.getController();
                    //System.out.println(controller.toString());
                    controller.setCircuit(circuit);

                    //controller.mainPane.setCenter(circuitDetailRoot);
                    //commenting that since it was not able to open new view on borderpane center
                    //open in a new window instead
                    Stage stage = new Stage();
                    stage.setTitle("Roadrevel");
                    stage.setScene(new Scene(circuitDetailRoot, 1200, 850));
                    stage.show();
                    //controller.mainPane.setBackground(Background.EMPTY);
                } catch (IOException ex) {
                    Logger.getLogger(EntryController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        };
        int columns = 0;
        int rows = 1;

        try {
            for (int i = 0; i < circuits.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/circuit.fxml"));
                HBox circuitBox = fxmlLoader.load();
                CircuitController circuitController = fxmlLoader.getController();
                circuitController.setData(circuits.get(i), circuitListener);
                if (columns == 1) {
                    columns = 0;
                    ++rows;

                }
                circuitGrid.add(circuitBox, columns++, rows);
                GridPane.setMargin(circuitBox, new Insets(10));

            }
        } catch (IOException ex) {
            Logger.getLogger(EntryController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
