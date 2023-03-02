/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package circuit_touristique.entities;

/**
 *
 * @author RFA2ABT
 */
public class Ville {
    String nom;
    String gouvernorat;
    String description;

    public Ville(String nom) {
        this.nom = nom;
    }

    public Ville(String nom, String gouvernorat, String description) {
        this.nom = nom;
        this.gouvernorat = gouvernorat;
        this.description = description;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getGouvernorat() {
        return gouvernorat;
    }

    public void setGouvernorat(String gouvernorat) {
        this.gouvernorat = gouvernorat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
