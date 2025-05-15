package com.example.vinlotteri.models;

public class Wine {
    private String name;
    private double price;

    public Wine(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
}
