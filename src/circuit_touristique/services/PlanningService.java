/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package circuit_touristique.services;

import circuit_touristique.entities.Circuit;
import circuit_touristique.entities.PlanningCircuit;
import circuit_touristique.utils.DataSource;
import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author arwar
 */
public class PlanningService {

    private Connection cnx = DataSource.getInstance().getCnx();

    public boolean isCircuitAvailable(Date date, int capacite) {
        String req = "SELECT* FROM planningcircuit WHERE dateDebut ='" + date + "' and capacite >=" + capacite;
        System.out.println(req);

        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            if (rs.next()) {
                // La requête a retourné au moins un résultat
                // Vous pouvez accéder aux colonnes du résultat en utilisant les méthodes getXXX du ResultSet
                return true;

            } else {
                // La requête n'a retourné aucun résultat
                return false;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;

    }

    public int getCapacity(int nc, Date date_dep) {
        String req = "SELECT capacite FROM planningcircuit WHERE dateDebut ='" + date_dep + "' and nc=" + nc;
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            if (rs.next()) {
                // La requête a retourné au moins un résultat
                // Vous pouvez accéder aux colonnes du résultat en utilisant les méthodes getXXX du ResultSet
                return Integer.parseInt(rs.getString("capacite"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return 0;
    }

    public void modifierCapacite(PlanningCircuit planning_circuit) {

        String req = "UPDATE planningcircuit SET capacite=" + planning_circuit.getCapacite() + " WHERE nc=" + planning_circuit.getNc() + " and dateDebut='" + planning_circuit.getDateDep() + "'";

        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Circuit modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
