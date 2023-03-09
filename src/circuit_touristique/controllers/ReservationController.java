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
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

/**
 * FXML Controller class
 *
 * @author RFA2ABT
 */
public class ReservationController implements Initializable {

    private int nbr_personnes;
    private Date date_debut_circuit;
    private Circuit circuit;
    @FXML
    private TextField tourist_vorname;
    @FXML
    private TextField tourist_name;
    @FXML
    private TextField tourist_email;
    @FXML
    private TextField payment_card_name;
    @FXML
    private TextField payment_card_number;
    @FXML
    private TextField card_expiration_date;
    @FXML
    private TextField card_security_code;
    @FXML
    private Button reserve_button;
    @FXML
    private Label tourist_vorname_control_label;
    @FXML
    private Label tourist_name_control_label;
    @FXML
    private Label tourist_email_control_label;
    final String senderEmailId = "roadrevel.app@gmail.com";
    final String senderPassword = "oczqfznhunocxyvp";
    final String emailSMTPserver = "smtp.gmail.com";
    final String emailSMTPPort = "587";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tourist_vorname_control_label.setTextFill(Color.RED);
        tourist_name_control_label.setTextFill(Color.RED);
        tourist_email_control_label.setTextFill(Color.RED);

        tourist_vorname_control_label.setVisible(false);
        tourist_vorname_control_label.setManaged(false);
        tourist_name_control_label.setVisible(false);
        tourist_name_control_label.setManaged(false);
        tourist_email_control_label.setVisible(false);
        tourist_email_control_label.setManaged(false);
    }

    public void setCircuitReservation(Circuit circuit, int nbr_personnes, Date date_debut_circuit) {
        this.circuit = circuit;
        this.date_debut_circuit = date_debut_circuit;
        this.nbr_personnes = nbr_personnes;

    }

    private class SMTPAuthenticator extends
            javax.mail.Authenticator {

        public PasswordAuthentication
                getPasswordAuthentication() {
            return new PasswordAuthentication(senderEmailId,
                    senderPassword);
        }
    }

    public void sendReservationConfirmationEmail(ReservationCircuit reservation) {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", emailSMTPserver);
        props.put("mail.smtp.port", emailSMTPPort);

        try {
            Authenticator auth = new SMTPAuthenticator();
            Session session = Session.getInstance(props, auth);
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmailId));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(tourist_email.getText()));

            message.setSubject("Confirmation Réservation");
            message.setContent("Bonjour", "text/html");

            Transport.send(message);
            System.out.println("Email send successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error in sending email.");
        }
    }

    @FXML
    private void confirm_reservation(ActionEvent event
    ) {

        tourist_vorname_control_label.setVisible(false);
        tourist_vorname_control_label.setManaged(false);
        tourist_name_control_label.setVisible(false);
        tourist_name_control_label.setManaged(false);
        tourist_email_control_label.setVisible(false);
        tourist_email_control_label.setManaged(false);

        // if the user added required data, proceed with reservation confirmation
        if (!tourist_vorname.getText().isEmpty() && !tourist_name.getText().isEmpty() && !tourist_email.getText().isEmpty()) {
            System.out.println("Confirm circuit reservation");

            ReservationCircuit reservation = new ReservationCircuit(156, circuit.getNc(), date_debut_circuit, nbr_personnes);
            ReservationService reservationService = new ReservationService();
            reservationService.ajouter(reservation);
            PlanningService planningService = new PlanningService();
            int nouvelleCapacite = planningService.getCapacity(circuit.getNc(), date_debut_circuit) - nbr_personnes;
            PlanningCircuit planning = new PlanningCircuit(circuit.getNc(), date_debut_circuit, nouvelleCapacite);
            planningService.modifierCapacite(planning);

            // send confirmation email to the user
            sendReservationConfirmationEmail(reservation);
        } else {
            // show error messages under empty fields
            System.out.println("One or more required fields are empty");
            if (tourist_vorname.getText().isEmpty()) {
                tourist_vorname_control_label.setText("Obligatoire");
                tourist_vorname_control_label.setVisible(true);
                tourist_vorname_control_label.setManaged(true);
            }
            if (tourist_name.getText().isEmpty()) {
                tourist_name_control_label.setText("Obligatoire");
                tourist_name_control_label.setVisible(true);
                tourist_name_control_label.setManaged(true);
            }
            if (tourist_email.getText().isEmpty()) {
                tourist_email_control_label.setText("Obligatoire");
                tourist_email_control_label.setVisible(true);
                tourist_email_control_label.setManaged(true);
            }

        }
    }

}
