package com.example.wishvault.ControllerTest;

import com.example.wishvault.controller.WishController;
import com.example.wishvault.model.Wish;
import com.example.wishvault.model.Wishlist;
import com.example.wishvault.service.WishService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WishController.class)
public class WishVaultControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WishService wishService;

    @Test
    void testGetHomePage() throws Exception {
        mockMvc.perform(get("/home"))
                .andExpect(status().isOk())
                .andExpect(view().name("WishVault"));
    }

    @Test
    void testGetCreatePage() throws Exception {
        mockMvc.perform(get("/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("CreateWishList"));
    }

    @Test
    void testPostWishList() throws Exception {
        when(wishService.getHighestId()).thenReturn(1);

        mockMvc.perform(post("/create")
                        .param("wisherName", "John")
                        .param("eventName", "Birthday"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/wishlist/1"));

        verify(wishService, times(1)).createWishlist(any(Wishlist.class));
        verify(wishService, times(1)).getHighestId();
    }


    @Test
    void testGetWishes() throws Exception {

        int id = 123;
        Wish wish = new Wish("Test Wish", "Description", "http://example.com", 10.0, "http:/example.com", 123);
        when(wishService.getWishesAsObject(id)).thenReturn(Collections.singletonList(wish));

        mockMvc.perform(get("/wishlist/{id}", id))
                .andExpect(status().isOk())
                .andExpect(view().name("Wishlist"))
                .andExpect(model().attributeExists("wishes"))
                .andExpect(model().attribute("wishes", Matchers.hasSize(1)))
                .andExpect(model().attribute("wishes",Matchers.contains(wish)))
                .andExpect(model().attributeExists("listId"));
    }



    @Test
    void testCreateWishForm() throws Exception {
        int id = 123;


        mockMvc.perform(get("/createWish/{id}", id))
                .andExpect(status().isOk())
                .andExpect(view().name("createWish"))
                .andExpect(model().attributeExists("listId"))
                .andExpect(model().attributeExists("wish"));
    }

    @Test
    void testPostWish() throws Exception {

        int id = 123;
        Wish wish = new Wish("Test Wish", "Description", "http://example.com", 10.0, "http://example.com",123);
        when(wishService.createWish(any(Wish.class) ,anyInt())).thenReturn(wish);

        mockMvc.perform(post("/createWish/{id}", id)
                        .param("name", wish.getName())
                        .param("description", wish.getDescription())
                        .param("itemUrl", wish.getItemUrl())
                        .param("price", String.valueOf(wish.getPrice()))
                        .param("imageUrl",wish.getImageUrl())
                        .param("id",String.valueOf(wish.getId())))

                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/wishlist/" + id));
    }
    @Test
    void testViewWishDetails() throws Exception {
        int wishId = 123;
        Wish wish = new Wish("Test Wish", "Description", "http://example.com", 10.0, "http://example.com", wishId);
        when(wishService.getWishById(anyInt())).thenReturn(wish);

        mockMvc.perform(get("/wish/{wishId}", wishId))
                .andExpect(status().isOk())
                .andExpect(view().name("showWish"))
                .andExpect(model().attributeExists("wish"))
                .andExpect(model().attribute("wish", wish));
    }

    @Test
    void testEditWishForm() throws Exception {
        int wishId = 123;
        Wish wish = new Wish("Test Wish", "Description", "http://example.com", 10.0, "http://example.com", wishId);
        when(wishService.getWishById(anyInt())).thenReturn(wish);

        mockMvc.perform(get("/editWish/{id}", wishId))
                .andExpect(status().isOk())
                .andExpect(view().name("editWish"))
                .andExpect(model().attributeExists("wish"))
                .andExpect(model().attribute("wish", wish));

    }

}



