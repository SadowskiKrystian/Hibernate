package org.example;

import java.time.LocalDate;

public class CarFilter {
    private String brand;
    private String model;
    private String colour;
    private LocalDate startDate;
    private LocalDate finishDate;

    private CarFilter(String brand, String model, String colour, LocalDate startDate, LocalDate finishDate) {
        this.brand = brand;
        this.model = model;
        this.colour = colour;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }
    static CarFilterBuilder builder(){
        return new CarFilterBuilder();
    }

    static class CarFilterBuilder{
        private String brand;
        private String model;
        private String colour;
        private LocalDate startDate;
        private LocalDate finishDate;

        CarFilterBuilder brand(String brand){
            this.brand = brand;
            return this;
        }
        CarFilterBuilder model(String model){
            this.model = model;
            return this;
        }
        CarFilterBuilder colour(String colour){
            this.colour = colour;
            return this;
        }
        CarFilterBuilder startDate(LocalDate startDate){
            this.startDate = startDate;
            return this;
        }
        CarFilterBuilder finishDate(LocalDate finishDate){
            this.finishDate = finishDate;
            return  this;
        }
        CarFilter build(){
            return new CarFilter(brand, model, colour, startDate, finishDate);
        }
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getColour() {
        return colour;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }
}
