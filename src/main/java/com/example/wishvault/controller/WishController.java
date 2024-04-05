package com.example.wishvault.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WishController {
    @GetMapping("/home")
    public String getHomePage() {


        return "WishVault";
    }
    @GetMapping("/create")
    public String getCreatePage() {

        return "CreateWishList";
    }



}
