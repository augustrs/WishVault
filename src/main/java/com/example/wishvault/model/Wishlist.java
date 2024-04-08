package com.example.wishvault.model;

import java.util.List;

public class Wishlist {
    private String wisherName;
    private String eventName;
    private List<Wish> wishes;


    public Wishlist(String wisherName, String eventName, List<Wish> wishes) {
        this.wisherName = wisherName;
        this.eventName = eventName;
        this.wishes = wishes;
    }

    public Wishlist() {

    }

    public String getWisherName() {
        return wisherName;
    }

    public String getEventName() {
        return eventName;
    }

    public List<Wish> getWishes() {
        return wishes;
    }

    public void setWisherName(String wisherName) {
        this.wisherName = wisherName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setWishes(List<Wish> wishes) {
        this.wishes = wishes;
    }
}
