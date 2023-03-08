/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package circuit_touristique.services;

import circuit_touristique.entities.Circuit;
import circuit_touristique.utils.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceCircuit {

    private Connection cnx = DataSource.getInstance().getCnx();

    public void ajouter(Circuit circuit) {
        String req = "INSERT INTO circuit(nom, description) VALUES('" + circuit.getNom() + "', '" + circuit.getDescription() + "');";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Personne ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifier(Circuit circuit) {
        String req = "UPDATE  circuit SET nom='" + circuit.getNom() + "', prenom= '" + circuit.getDescription() + "' WHERE id=" + circuit.getNc() + "";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Circuit modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimer(Circuit circuit) {
        String req = "DELETE FROM circuit WHERE id=" + circuit.getNc();
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Circuit supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Circuit> afficher() {
        List<Circuit> list = new ArrayList<>();
        String req = "SELECT * FROM circuit";
        try {
            ;
            Statement st = cnx.createStatement();
            ResultSet result = st.executeQuery(req);
            while (result.next()) {
                list.add(new Circuit(result.getInt("nc"), result.getString("vdep"), result.getString("varr"), result.getFloat("prix"), result.getInt("duree"), result.getString("nom"), result.getString("description"), result.getString("imageSrc")));
            }
            System.out.println("Circuits récupérés !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

}
