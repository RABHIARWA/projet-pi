/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package circuit_touristique.entities;

public class Etape {
    private int nc;
    private int rang;
    private String ville;
    private int jr;
    private String programme;

   
    

    public Etape(int nc, int rang, String ville, int jr, String programme) {
        this.nc = nc;
        this.rang = rang;
        this.ville = ville;
        this.jr= jr;
        this.programme = programme;
    }

    public int getNc() {
        return nc;
    }

    public void setNc(int nc) {
        this.nc = nc;
    }

    public int getRang() {
        return rang;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getJr() {
        return jr;
    }

    public void setJr(int jr) {
        this.jr = jr;
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }
    
    
}
