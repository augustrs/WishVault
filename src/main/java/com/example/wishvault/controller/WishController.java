package com.example.wishvault.controller;

import com.example.wishvault.model.Wish;
import com.example.wishvault.model.Wishlist;
import com.example.wishvault.service.WishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class WishController {

    private WishService wishService;

    public WishController(WishService wishService) {
        this.wishService = wishService;
    }

    @GetMapping("/")
    public String redirectToHomePage() {
        return "redirect:/home";
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
    public String postWishList(@ModelAttribute Wishlist wishlist, RedirectAttributes redirectAttributes) {

        try {
            wishService.createWishlist(wishlist);
            int id = wishService.getHighestId();
            return "redirect:/wishlist/" + id;
        }

        catch (DatabaseOperationException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/error";
        }
    }

    @GetMapping("wishlist/{id}")
    public String getWishes(@PathVariable int id, Model model, RedirectAttributes redirectAttributes) {

        try {
            List<Wish> wishes = wishService.getWishesAsObject(id);
            String name = wishService.findNameFromId(id);
            model.addAttribute("wishes", wishes);
            model.addAttribute("listId", id);
            model.addAttribute("wisherName", name);
            return "Wishlist";
        }

        catch (WishlistNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/error";
        }
    }

    @PostMapping("/createWish/{id}")
    public String postWish(@PathVariable("id") int id, @ModelAttribute Wish wish, RedirectAttributes redirectAttributes) {

        try {
            wish = wishService.createWish(wish, id);
            int wishId = wishService.getHighestWishId(id);
            wishService.saveImage(wishId, wish.getImageUrl());
            System.out.println(wish);
            return "redirect:/wishlist/" + id;
        }

        catch (WishlistNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/error";
        }
    }

    @GetMapping("/createWish/{id}")
    public String createWishForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("listId",id);
        model.addAttribute("wish", new Wish());
        return "createWish";

    }

    @GetMapping("/wish/{wishId}")
    public String viewWishDetails(@PathVariable int wishId, Model model, RedirectAttributes redirectAttributes) {

        try {
            Wish wish = wishService.getWishById(wishId);
            model.addAttribute("wish", wish);
            return "showWish";
        }

        catch (DatabaseOperationException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/error";
        }
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


    @PostMapping("/deleteWish/{id}")
    public String deleteWish(@PathVariable int id, RedirectAttributes redirectAttributes) throws SQLException {
        Wish wishToDelete = wishService.getWishById(id);
        wishService.deleteWishImage(id);
        wishService.deleteWish(id);
        int wishListId = wishToDelete.getListId();
        redirectAttributes.addAttribute("id",wishListId);
        return "redirect:/wishlist/" + wishListId;
    }

    @GetMapping("/wishlists")
    public String showAllWishlist(Model model) throws SQLException {
        List<Wishlist> wishlists = wishService.getAllWishlist();
        model.addAttribute("wishlists",wishlists);
        return "showWishLists";
    }

    @PostMapping("/deleteWishlist/{id}")
    public String deleteWishlist(@PathVariable int id, RedirectAttributes redirectAttributes) throws SQLException {
        wishService.deleteWishlist(id);
        return "redirect:/wishlists";
    }




    }
