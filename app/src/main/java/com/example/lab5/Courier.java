package com.example.lab5;

import java.util.ArrayList;
import java.util.Random;

public class Courier {
    private String fullName;
    private String specialization;
    private ArrayList<Order> orders = new ArrayList<>();

    public Courier(String fullName, String specialization) {
        this.fullName = fullName;
        this.specialization = specialization;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }
}
