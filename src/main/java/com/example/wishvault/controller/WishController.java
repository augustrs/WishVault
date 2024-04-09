package com.example.wishvault.controller;

import com.example.wishvault.model.Wishlist;
import com.example.wishvault.service.WishService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String getCreatePage() {

        return "CreateWishList";
    }

    @PostMapping("/create")
    public void postWishList(@ModelAttribute Wishlist wishlist) {

    }



}
