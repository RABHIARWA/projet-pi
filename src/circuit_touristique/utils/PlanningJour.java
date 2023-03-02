/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package circuit_touristique.utils;

import java.util.List;

public class PlanningJour {

    private int jour;
    private String description;
    private List<String> villes;

    public PlanningJour(int jour, String description, List<String> villes) {
        this.jour = jour;
        this.villes = villes;
        this.description = description;
    }

    public int getJour() {
        return jour;
    }

    public void setJour(int jour) {
        this.jour = jour;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getVilles() {
        return villes;
    }

    public void setVilles(List<String> villes) {
        this.villes = villes;
    }

}
