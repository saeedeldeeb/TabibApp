package com.example.eldeeb.tabibapp;

public class doctors {
    private String doctor_name;
    private String doctor_image;
    private String doctor_description;
    private double rating;
    private double price;
    private String fromDays;
    private String toDays;
    private String fromHours;
    private String toHours;
    private String governarator;
    private String region;
    private String specialiazation;

    public doctors(String doctor_name, String doctor_image, String doctor_description, double rating, double price, String fromDays, String toDays, String fromHours, String toHours, String governarator, String region, String specialiazation) {
        this.doctor_name = doctor_name;
        this.doctor_image = doctor_image;
        this.doctor_description = doctor_description;
        this.rating = rating;
        this.price = price;
        this.fromDays = fromDays;
        this.toDays = toDays;
        this.fromHours = fromHours;
        this.toHours = toHours;
        this.governarator = governarator;
        this.region = region;
        this.specialiazation = specialiazation;
    }


    public String getDoctor_name() {
        return doctor_name;
    }

    public String getDoctor_image() {
        return doctor_image;
    }

    public String getDoctor_description() {
        return doctor_description;
    }

    public double getRating() {
        return rating;
    }

    public double getPrice() {
        return price;
    }

    public String getFromDays() {
        return fromDays;
    }

    public String getToDays() {
        return toDays;
    }

    public String getFromHours() {
        return fromHours;
    }

    public String getToHours() {
        return toHours;
    }

    public String getGovernarator() {
        return governarator;
    }

    public String getRegion() {
        return region;
    }

    public String getSpecialiazation() {
        return specialiazation;
    }
}
