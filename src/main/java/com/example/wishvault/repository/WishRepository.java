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
                wishlist.setListId((int) generatedId);
            }

        }
    }

    public Wish createWish(Wish wish, int listId) throws SQLException {
        Connection connection = ConnectionManager.getConnection(db_url, username, pwd);
        String SQL = "INSERT INTO WISH(NAME,DESCRIPTION,ITEMURL,PRICE,LISTID) VALUES (?,?,?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, wish.getName());
            ps.setString(2, wish.getDescription());
            ps.setString(3, wish.getItemUrl());
            ps.setDouble(4, wish.getPrice());
            ps.setInt(5,listId);

            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt(1);

            return wish;


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
            wish.setName(rs.getString("NAME"));
            wish.setDescription(rs.getString("DESCRIPTION"));
            wish.setPrice(rs.getDouble("PRICE"));
            wish.setItemUrl(rs.getString("ITEMURL"));

            wishes.add(wish);
        }
        return wishes;
    }
    public int getHighestId() throws SQLException {
        int id = 0;
        Connection connection = ConnectionManager.getConnection(db_url,username,pwd);
        String SQL = "SELECT MAX(LISTID) FROM WISHLIST";
        PreparedStatement ps = connection.prepareStatement(SQL);

        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt("MAX(LISTID)");
    }


}
