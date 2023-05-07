package org.example;

public class CarFilter {
    private String brand;
    private String model;
    public CarFilter() {
    }


    public CarFilter(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }
}
