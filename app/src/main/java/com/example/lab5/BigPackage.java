package com.example.lab5;

public class BigPackage extends Package {
    private double weight;

    public BigPackage() {
        setSize("Big");
        setTransportationRequirements("Driver");
    }

    public BigPackage(boolean fragility, double weight) {
        setSize("Big");
        setTransportationRequirements("Driver");
        setFragility(fragility);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String getPackageType() {
        return "Big Package";
    }
}
