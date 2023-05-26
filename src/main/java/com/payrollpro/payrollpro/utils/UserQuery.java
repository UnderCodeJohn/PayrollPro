package com.payrollpro.payrollpro.utils;

import com.payrollpro.payrollpro.model.Employee;
import com.payrollpro.payrollpro.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class UserQuery {
    public static ObservableList<User> getAllUsers() throws SQLException {
        ObservableList<User> returnAllUsers = FXCollections.observableArrayList();
        String sql = "SELECT * FROM users";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int userID = rs.getInt("User_ID");
            String username = rs.getString("Username");
            String password = rs.getString("Password");
            int isAdmin = rs.getInt("Admin");

            User newUser = new User(userID, username, password, isAdmin);
            returnAllUsers.add(newUser);
        }
        return returnAllUsers;
    }

    public static void addUser(User user) throws SQLException {
        String insertUserSql = "INSERT INTO users (Username, Password, Admin)\n" +
                "VALUES (?, ?, 1);";

        // Insert User
        PreparedStatement insertUserPs = JDBC.connection.prepareStatement(insertUserSql);
        insertUserPs.setString(1, user.getUsername());
        insertUserPs.setString(2, user.getPassword());
        insertUserPs.executeUpdate();
    }

    public static void updateUser(User user) throws SQLException {
        String sql = "UPDATE users SET username = ?, password = ? WHERE User_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        ps.setInt(3, user.getUserId());

        ps.executeUpdate();
    }

    public static void deleteUser(User user) throws SQLException {
        // Update the foreign key column in the employees table
        String updateSQL = "UPDATE employees SET User_ID = NULL WHERE User_ID = ?";

        // Delete the user from the users table
        String deleteSQL = "DELETE FROM users WHERE User_ID = ?";

        try (PreparedStatement updatePs = JDBC.connection.prepareStatement(updateSQL)) {
            updatePs.setInt(1, user.getUserId());
            updatePs.executeUpdate();
        } catch (SQLException e) {
            // Handle specific exception for the update statement
            System.out.println("Error executing update statement: " + e.getMessage());
        }

        try (PreparedStatement deletePs = JDBC.connection.prepareStatement(deleteSQL)) {
            deletePs.setInt(1, user.getUserId());
            deletePs.executeUpdate();
        } catch (SQLException e) {
            // Handle specific exception for the delete statement
            System.out.println("Error executing delete statement: " + e.getMessage());
        }
    }
}
