package com.example.wishvault.exceptions;

public class WishlistNotFoundException extends RuntimeException {

    public WishlistNotFoundException(String message) {
        super(message);
    }

}