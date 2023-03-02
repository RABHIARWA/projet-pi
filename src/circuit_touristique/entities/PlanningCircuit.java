/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package circuit_touristique.entities;

import java.util.Date;


public class PlanningCircuit {
    private int nc;
    private Date dateDep;
    private int capacite;

    public PlanningCircuit(int nc, Date dateDep, int capacite) {
        this.nc = nc;
        this.dateDep = dateDep;
        this.capacite = capacite;
    }

    public int getNc() {
        return nc;
    }

    public void setNc(int nc) {
        this.nc = nc;
    }

    public Date getDateDep() {
        return dateDep;
    }

    public void setDateDep(Date dateDep) {
        this.dateDep = dateDep;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }
    
}
