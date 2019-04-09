package com.codecool.dao.database;

import com.codecool.model.Image;


import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseImageDao extends AbstractDao {


    public DatabaseImageDao(Connection connection) {
        super(connection);
    }

    public Image getImage(int id) throws SQLException, IOException {
        String sql="SELECT * FROM image WHERE id = ?";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Image image = new Image(resultSet.getString("image_id"), resultSet.getBytes("image"), resultSet.getString("name"));
                return image;
            }

        } return null;
    }

    public List<Image> getDefaultImages() throws SQLException, IOException{
        String sql="SELECT * FROM image WHERE id > 0 AND id < 7";
        List<Image> defaultImages = new ArrayList<>();
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                defaultImages.add(new Image(resultSet.getString("image_id"), resultSet.getBytes("image"), resultSet.getString("name")));
            }

        } return defaultImages;

    }

    public void addImage(Image image) throws SQLException {

        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);

        String sql = "INSERT INTO image VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, image.getImage_id());
            statement.setString(2, image.getName());
            statement.setBytes(3, image.getImageBytea());
            executeInsert(statement);
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }

    public void updateImage(Image image) throws SQLException{
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "UPDATE image SET name=?, image=? where image_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, image.getName());
            statement.setBytes(2, image.getImageBytea());
            statement.setString(3, image.getImage_id());
            executeInsert(statement);
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            throw ex; //??
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }

    /*public void setImageToUser(Image image, User user){

        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);

        String sql = "INSERT INTO image VALUES (?, ?, ?)";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString();
        }
    }*/




}
