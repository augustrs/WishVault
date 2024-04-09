package com.example.wishvault.controller;

import com.example.wishvault.model.Wish;
import com.example.wishvault.model.Wishlist;
import com.example.wishvault.service.WishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
public class WishController {
    private WishService wishService;
    public WishController(WishService wishService) {
        this.wishService = wishService;
    }

    @GetMapping("/home")
    public String getHomePage() {


        return "WishVault";
    }
    @GetMapping("/create")
    public String getCreatePage(Model model) {
    Wishlist defaultWishlist = new Wishlist();
    model.addAttribute("wishList", defaultWishlist);
        return "CreateWishList";
    }

    @PostMapping("/create")
    public String postWishList(@ModelAttribute Wishlist wishlist) throws SQLException {
    wishService.createWishlist(wishlist);
    return "redirect:/home";
    }

    @GetMapping("wishlist/{id}")
    public String getWishes(@PathVariable int id, Model model) throws SQLException {
        List<Wish> wishes = wishService.getWishesAsObject(id);
        model.addAttribute("wishes",wishes);
        return "Wishlist";
    }




}
