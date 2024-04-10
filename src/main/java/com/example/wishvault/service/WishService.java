package com.example.wishvault.service;

import com.example.wishvault.model.Wish;
import com.example.wishvault.model.Wishlist;
import com.example.wishvault.repository.WishRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class WishService {
    private WishRepository wishRepository;

    public WishService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    public void createWishlist(Wishlist wishlist) throws SQLException {
        wishRepository.createWishlist(wishlist);
    }
    public List<Wish> getWishesAsObject(int id) throws SQLException {
       return wishRepository.getWishesAsObject(id);
    }


    public Wish createWish(Wish wish, int listId) throws SQLException {
        return wishRepository.createWish(wish,listId);
    }

    //TODO Finish this method

}
