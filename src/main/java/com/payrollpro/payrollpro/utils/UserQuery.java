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

}
