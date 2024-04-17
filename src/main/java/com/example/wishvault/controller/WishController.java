package com.example.wishvault.controller;

import com.example.wishvault.model.Wish;
import com.example.wishvault.model.Wishlist;
import com.example.wishvault.service.WishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    int id = wishService.getHighestId();
    return "redirect:/wishlist/" + id;
    }

    @GetMapping("wishlist/{id}")
    public String getWishes(@PathVariable int id, Model model) throws SQLException {
        List<Wish> wishes = wishService.getWishesAsObject(id);
        String name = wishService.findNameFromId(id);
        model.addAttribute("wishes",wishes);
        model.addAttribute("listId",id);
        model.addAttribute("wisherName",name);
        return "Wishlist";
    }


    @PostMapping("/createWish/{id}")
    public String postWish(@PathVariable("id") int id, @ModelAttribute Wish wish, Model model) throws SQLException {
        wish = wishService.createWish(wish,id);
        int wishId = wishService.getHighestWishId(id);
        wishService.saveImage(wishId,wish.getImageUrl());
        System.out.println(wish);
        return "redirect:/wishlist/" + id;

    }

    @GetMapping("/createWish/{id}")
    public String createWishForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("listId",id);
        model.addAttribute("wish", new Wish());
        return "createWish";

    }

    @GetMapping("/wish/{wishId}")
    public String viewWishDetails(@PathVariable int wishId, Model model) throws SQLException {
        Wish wish = wishService.getWishById(wishId);
        model.addAttribute("wish", wish);
        return "showWish";
    }

    @GetMapping("/editWish/{id}")
    public String editWishForm(@PathVariable int id, Model model) throws SQLException {
        Wish wish = wishService.getWishById(id);
        model.addAttribute("wish", wish);

        return "editWish";
    }
    @PostMapping("editWish/{id}")
    public String editWish(@PathVariable int id, @ModelAttribute Wish updatedwish) throws SQLException {
        wishService.updateWish(updatedwish);
        return "redirect:/wish/" + id;
    }








}
