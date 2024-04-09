package com.example.wishvault.repository;

import com.example.wishvault.model.Wish;
import com.example.wishvault.model.Wishlist;
import com.example.wishvault.util.ConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WishRepository {

    @Value("${spring.datasource.url}")
    private String db_url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String pwd;


    public void createWishlist(Wishlist wishlist) throws SQLException {
        Connection connection = ConnectionManager.getConnection(db_url, username, pwd);
        String SQL = "INSERT INTO WISHLIST(WISHERNAME,EVENTNAME)" + "values(?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, wishlist.getWisherName());
            ps.setString(2, wishlist.getEventName());

            int rows = ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                long generatedId = rs.getLong(1);
                wishlist.setListId(generatedId);
            }

        }
    }

    public List<Wish> getWishesAsObject(int id) throws SQLException {
        List<Wish> wishes = new ArrayList<>();
        Connection connection = ConnectionManager.getConnection(db_url, username, pwd);
        String SQL = "SELECT * FROM WISH WHERE LISTID = ?";
        PreparedStatement ps = connection.prepareStatement(SQL);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Wish wish = new Wish();
            wish.setItemName(rs.getString("NAME"));
            wish.setDescription(rs.getString("DESCRIPTION"));
            wish.setPrice(rs.getDouble("PRICE"));
            wish.setItemUrl(rs.getString("ITEMURL"));

            wishes.add(wish);
        }
        return wishes;
    }


}
