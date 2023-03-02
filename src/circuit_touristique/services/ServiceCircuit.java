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
        String req = "INSERT INTO personne(nom, prenom) VALUES('" + circuit.getNom() + "', '" + circuit.getDescription() + "');";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Personne ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifier(Circuit circuit) {
        String req = "UPDATE  personne SET nom='" + circuit.getNom() + "', prenom= '" + circuit.getDescription() + "' WHERE id=" + circuit.getNc() + "";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Personne modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimer(Circuit circuit) {
        String req = "DELETE FROM personne WHERE id=" + circuit.getNc();
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Personne supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Circuit> afficher() {
        List<Circuit> list = new ArrayList<>();
        String req = "SELECT * FROM personne";
        try {
            Statement st = cnx.createStatement();
            ResultSet result = st.executeQuery(req);
            while (result.next()) {
                list.add(new Circuit(result.getInt("nc"), result.getString("vdep"), result.getString("varr"), result.getFloat("prix"), result.getInt("duree"), result.getString("nom"), result.getString("description"), result.getString("imageSrc")));
            }
            System.out.println("Personnes récupérées !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public List<Circuit> getTestData() {
        List<Circuit> list = new ArrayList<>();

        Circuit circuit = new Circuit(1, "Tunis", "Tunis", 850, 8, "Circuit L'Essentiel de la Tunisie", "Un circuit justement équilibré qui permet de découvrir, du nord au sud les principaux aspects de la Tunisie, tant culturels que géographiques. Les paysages les plus exceptionnels succèdent aux découvertes historiques, archéologiques et culturelles, à un rythme agréable.", "circuit_touristique/images/circuit_1.jpg");
        list.add(circuit);
        circuit = new Circuit(2, "Tatouine", "Matmata", 300, 2, "Tatouine - Chenenni - Ksar Ghilane - Matmata : visite de 2 jours", "Découvrez les points forts du sud de la Tunisie lors d'une visite en petit groupe de Djerba à Tatouine, Chenenni, Ksar Ghuilaine et Matmata.", "circuit_touristique/images/circuit_2.jpg");
        list.add(circuit);
        circuit = new Circuit(3, "Djerba", "Djerba", 150, 2, "OASIS SAFARI : 2 JOURS Départ Djerba & Zarzis", "Découvrez écouvrir le charme de la Tunisie authentique à travers ses programmes inédits: circuits en 4x4 en plein déserts, randonnées chamelières, méharées, circuits culturels et archéologiques.", "circuit_touristique/images/circuit_3.jpg");
        list.add(circuit);
        return list;
    }
}
