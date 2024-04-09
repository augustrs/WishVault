package com.example.wishvault.model;


public class Wish {
    private String itemName;
    private String description;
    private String itemUrl;
    private double price;

    public Wish(String itemName, String description, String itemUrl, double price) {
        this.itemName = itemName;
        this.description = description;
        this.itemUrl = itemUrl;
        this.price = price;
    }

    public Wish() {

    }

    public String getItemName() {
        return itemName;
    }

    public String getDescription() {
        return description;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
