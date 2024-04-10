package com.example.wishvault.model;


public class Wish {
    private String name;
    private String description;
    private String itemUrl;
    private double price;
    private int id;
    private int listId;

    public Wish(String name, String description, String itemUrl, double price) {
        this.name = name;
        this.description = description;
        this.itemUrl = itemUrl;
        this.price = price;
    }

    public Wish() {

    }

    public String getName() {
        return name;
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

    public void setName(String name) {
        this.name = name;
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

    public void setId(int id) {
        this.id = id;
    }
}
