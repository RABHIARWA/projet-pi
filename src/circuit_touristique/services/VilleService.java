/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package circuit_touristique.services;

import circuit_touristique.entities.Ville;
import circuit_touristique.utils.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author arwar
 */
public class VilleService {
    private Connection cnx = DataSource.getInstance().getCnx();
    public Ville setVilleData(Ville ville){
         String req = "SELECT latitude,longitude FROM ville WHERE nom='" + ville.getNom()+ "'";
         System.out.println(req);
        try {
            Statement st = cnx.createStatement();
            ResultSet result = st.executeQuery(req);
            while (result.next()) {
                System.out.println(result.getFloat("latitude"));
                System.out.println(result.getFloat("longitude"));
                ville.setLatitude(result.getFloat("latitude"));
                ville.setLongitude(result.getFloat("longitude"));
            }
            System.out.println("Villes du circuit récupérées !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(ville.toString());
        return ville;
        
    }
}
