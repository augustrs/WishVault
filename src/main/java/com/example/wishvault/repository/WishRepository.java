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

    public void saveImage(int wishId, String imageUrl) throws SQLException {
        Connection connection = ConnectionManager.getConnection(db_url,username,pwd);
        String SQL = "INSERT INTO IMAGE (IMAGE, WISHID) VALUES (?,?)";
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setString(1,imageUrl);
            ps.setInt(2,wishId);
            ps.executeUpdate();
        }
    }

    public String findNameFromId(int id) throws SQLException {
        Connection connection = ConnectionManager.getConnection(db_url,username,pwd);
        String SQL = "SELECT WISHERNAME FROM WISHLIST WHERE LISTID = ?";
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();
            rs.next();
            String name = rs.getString("WISHERNAME").toLowerCase();
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            return name;
        }
    }
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

    public String findImageUrl(int id) throws SQLException {
        Connection connection = ConnectionManager.getConnection(db_url,username,pwd);
        String SQL = "SELECT IMAGE FROM IMAGE WHERE WISHID = ?";
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getString("IMAGE");
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
            String imageUrl = findImageUrl(rs.getInt("WISHID"));
            Wish wish = new Wish();
            wish.setId(rs.getInt("WISHID"));
            wish.setName(rs.getString("NAME"));
            wish.setDescription(rs.getString("DESCRIPTION"));
            wish.setPrice(rs.getDouble("PRICE"));
            wish.setItemUrl(rs.getString("ITEMURL"));
            wish.setImageUrl(imageUrl);

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
    public int getHighestWishId(int id) throws SQLException {
        Connection connection = ConnectionManager.getConnection(db_url,username,pwd);
        String SQL = "SELECT MAX(WISHID) FROM WISH WHERE LISTID = ?";
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt("MAX(WISHID)");
        }
    }

    public Wish getWishById(int wishId) throws SQLException {
        Connection connection = ConnectionManager.getConnection(db_url,username,pwd);
        String SQL = "SELECT * FROM WISH WHERE WISHID = ?";


        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setInt(1, wishId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String imageUrl = findImageUrl(rs.getInt("WISHID"));
                Wish wish = new Wish();
                wish.setId(rs.getInt("WISHID"));
                wish.setName(rs.getString("NAME"));
                wish.setDescription(rs.getString("DESCRIPTION"));
                wish.setItemUrl(rs.getString("ITEMURL"));
                wish.setPrice(rs.getDouble("PRICE"));
                wish.setListId(rs.getInt("LISTID"));
                wish.setImageUrl(imageUrl);

                return wish;
            } else {
                return null;
            }

        }
    }


}
