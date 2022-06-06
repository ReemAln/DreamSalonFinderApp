package com.example.dreamsalonfinderapp;

public class Services {
    String name, typeServices, time;
    int image, isSelected;
    float rating;


    public Services(String name, String type, String time, int image, float rating, int isSelected) {
        this.name = name;
        this.typeServices = type;
        this.time = time;
        this.image = image;
        this.rating = rating;
        this.isSelected = isSelected;
    }
}