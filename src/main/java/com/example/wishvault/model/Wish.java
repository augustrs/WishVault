package com.example.wishvault.model;


public class Wish {
    private String name;
    private String description;
    private String itemUrl;
    private double price;
    private int id;
    private int listId;
    private String imageUrl;


    public Wish(String name, String description, String itemUrl, double price, String imageUrl, int id) {
        this.name = name;
        this.description = description;
        this.itemUrl = itemUrl;
        this.price = price;
        this.imageUrl = imageUrl;
        this.id = id;
    }

    public Wish() {

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getListId() {
        return listId;
    }

    public String getImageUrl() {
        return imageUrl;
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
