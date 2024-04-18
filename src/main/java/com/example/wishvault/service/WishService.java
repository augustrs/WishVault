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

    public void createWishlist(Wishlist wishlist) {
        wishRepository.createWishlist(wishlist);
    }
    public List<Wish> getWishesAsObject(int id) {
       return wishRepository.getWishesAsObject(id);
    }
    public String findNameFromId(int id) {
        return wishRepository.findNameFromId(id);
    }

    public Wish createWish(Wish wish, int listId) {
        return wishRepository.createWish(wish,listId);
    }

    public int getHighestId() {
        return wishRepository.getHighestId();
    }

    public void saveImage(int wishId, String imageUrl) {
        wishRepository.saveImage(wishId,imageUrl);
    }

    public int getHighestWishId(int wishId) {
        return wishRepository.getHighestWishId(wishId);
    }

    public Wish getWishById(int wishid) {
        return wishRepository.getWishById(wishid);
    }
}
