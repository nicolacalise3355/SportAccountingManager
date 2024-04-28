package com.nicolacalise.SportAccountingManager.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Workday {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String date;
    private int revenue;
    private int costs;

    public Workday(String date, int revenue, int costs) {
        this.date = date;
        this.revenue = revenue;
        this.costs = costs;
    }

    public Workday() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getCosts() {
        return costs;
    }

    public void setCosts(int costs) {
        this.costs = costs;
    }
}
