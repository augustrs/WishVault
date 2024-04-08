package com.example.wishvault.service;

import com.example.wishvault.model.Wishlist;
import com.example.wishvault.repository.WishRepository;
import org.springframework.stereotype.Service;

@Service
public class WishService {
    private WishRepository wishRepository;

    public WishService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    //TODO Finish this method

}
