/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package circuit_touristique.utils;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author RFA2ABT
 */
public class PlannedCircuit {
    String circuit_name;
    LocalDate circuit_date;
    float price_total;

    public PlannedCircuit(String circuit_name, LocalDate circuit_date, float price_total) {
        this.circuit_name = circuit_name;
        this.circuit_date = circuit_date;
        this.price_total = price_total;
    }

    
    public String getCircuit_name() {
        return circuit_name;
    }

    public void setCircuit_name(String circuit_name) {
        this.circuit_name = circuit_name;
    }

    public LocalDate getCircuit_date() {
        return circuit_date;
    }

    public void setCircuit_date(LocalDate circuit_date) {
        this.circuit_date = circuit_date;
    }

    public float getPrice_total() {
        return price_total;
    }

    public void setPrice_total(float price_total) {
        this.price_total = price_total;
    }
    
    
}
