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
    public String findNameFromId(int id) throws SQLException {
        return wishRepository.findNameFromId(id);
    }


    public Wish createWish(Wish wish, int listId) throws SQLException {
        return wishRepository.createWish(wish,listId);
    }
    public int getHighestId() throws SQLException {
        return wishRepository.getHighestId();
    }

    public void saveImage(int wishId, String imageUrl) throws SQLException {
        wishRepository.saveImage(wishId,imageUrl);
    }

    public int getHighestWishId(int wishId) throws SQLException {
        return wishRepository.getHighestWishId(wishId);
    }

    public Wish getWishById(int wishid) throws SQLException {
        return wishRepository.getWishById(wishid);
    }

    public void updateWish(Wish updatedwish)  throws SQLException{
        wishRepository.updateWish(updatedwish);
    }

    public void deleteWish(int id) throws SQLException {
        wishRepository.deleteWish(id);
    }

    public void deleteWishImage(int id) throws SQLException {
        wishRepository.deleteWishImage(id);
    }
    public List<Wishlist> getAllWishlist() throws SQLException {
        return wishRepository.getAllWishList();
    }
    public void deleteWishlist(int id) throws SQLException {
        wishRepository.deleteWishlist(id);
    }


}
