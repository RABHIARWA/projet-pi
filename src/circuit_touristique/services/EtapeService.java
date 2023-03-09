/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package circuit_touristique.services;

import circuit_touristique.entities.Etape;
import circuit_touristique.utils.DataSource;
import circuit_touristique.utils.PlanningJour;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EtapeService {

    private Connection cnx = DataSource.getInstance().getCnx();

    public List<Etape> getStepsForCircuit(int nc) {
        List<Etape> list = new ArrayList<>();
        //Etape(int nc, int rang, String ville, int nbj, String programme)
        String req = "SELECT * FROM etape WHERE nc=" + nc;
        try {
            Statement st = cnx.createStatement();
            ResultSet result = st.executeQuery(req);
            while (result.next()) {
                list.add(new Etape(result.getInt("nc"), result.getInt("rang"), result.getString("ville"), result.getInt("jr"), result.getString("programme")));
            }
            System.out.println("Personnes récupérées !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public List<Etape> getTestData() {
        List<Etape> list = new ArrayList<>();
        Etape etape = new Etape(1, 1, "Tunis", 1, "Accueil par notre correspondant et transfert à l'hôtel. Dîner nuit à Tunis.");
        list.add(etape);
        etape = new Etape(1, 2, "Kairouan", 2, "Départ pour Kairouan, première ville sacrée du Maghreb. Visite de la Grande Mosquée Oqba Ibn Nefaa, le plus ancien et l'un des plus prestigieux édifices de Tunisie, puis du mausolée du barbier Sidi Sahbi et des bassins des \"Aghlabites\". Découverte de la Médina et des souks, particulièrement réputés pour ses tapis à points noués. Déjeuner.\n");
        list.add(etape);
        etape = new Etape(1, 3, "Tozeur", 2, "Continuation vers Tozeur.Dîner nuit à Tozeur");
        list.add(etape);
        etape = new Etape(1, 4, "Tozeur", 3, "Matinée libre. Possibilité d'effectuer une excursion à la découverte des oasis de montagne de Tamerza et Chebika en 4x4 (à réserver et à régler sur place : environ 25€). Déjeuner à l\'hôtel. Visite guidée de Tozeur, petite ville aussi généreuse par sa nature que par ses habitants, insolite et surprenante avec son immense oasis saharienne, sa médina aux constructions typiques couleur sable... Tozeur ne manquera pas de vous charmer ! Puis, départ pour une escapade en direction de Nefta et de son oasis surnommée \"la corbeille\", immense tâche verte au milieu d\'un paysage désertique, célèbre pour sa fraîcheur et ses mille sources. Possibilité de promenade en calèche (à réserver et à régler sur place : environ 10€). Dîner nuit.");
        list.add(etape);
        return list;
    }

    public PlanningJour getPlanningJour(int j, List<Etape> etapes) {
        String planningDescription = "";
        List<String> planningVilles = new ArrayList<String>();
        for (Etape etape : etapes) {
            if (etape.getJr() == j) {
                planningDescription += etape.getProgramme();
                planningVilles.add(etape.getVille());

            }

        }
        return new PlanningJour(j, planningDescription, planningVilles);

    }

    public List<String> getCircuitVilles(int nc) {
        List<String> villes = new ArrayList<String>();
        String req = "SELECT DISTINCT ville FROM etape WHERE nc=" + nc;
        try {
            Statement st = cnx.createStatement();
            ResultSet result = st.executeQuery(req);
            while (result.next()) {
                villes.add(result.getNString("ville"));
            }
            System.out.println("Villes du circuit récupérées !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(villes.toString());
        return villes;

    }

}
