/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package circuit_touristique.entities;

import java.util.Date;

/**
 *
 * @author arwar
 */
public class ReservationCircuit {
    
    private int id_client;
    private int nc;
    private int num_reservation;
    private Date date_debut_circuit;
    private int nbr_places;

    public ReservationCircuit(int id_client, int nc, Date date_debut_circuit, int nbr_places) {
        this.id_client = id_client;
        this.nc = nc;
        this.date_debut_circuit = date_debut_circuit;
        this.nbr_places = nbr_places;
    }

    /**
     *
     * @return
     */
    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getNc() {
        return nc;
    }

    public void setNc(int nc) {
        this.nc = nc;
    }

    public int getNum_reservation() {
        return num_reservation;
    }

    public void setNum_reservation(int num_reservation) {
        this.num_reservation = num_reservation;
    }

    public Date getDate_debut_circuit() {
        return date_debut_circuit;
    }

    public void setDate_debut_circuit(Date date_debut_circuit) {
        this.date_debut_circuit = date_debut_circuit;
    }

    public int getNbr_places() {
        return nbr_places;
    }

    public void setNbr_places(int nbr_places) {
        this.nbr_places = nbr_places;
    }

   
    
    
    
}
