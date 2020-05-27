package com.example.myapplication.beautifulplaces;

public class Place {
    private String place;
    private String description;
    private String oldPrice;
    private String newPrice;
    private String picture;

    public Place(String place, String description, String oldPrice, String newPrice) {
        this.place = place;
        this.description = description;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
    }

    public String getPlace() {
        return place;
    }

    public String getDescription() {
        return description;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public String getNewPrice() {
        return newPrice;
    }

    public String getPicture() {
        return picture;
    }
}
