package com.payrollpro.payrollpro.model;

import com.payrollpro.payrollpro.utils.EmployeeQuery;
import com.payrollpro.payrollpro.utils.UserQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class User {
    private int userId;
    private String username;
    private String password;
    private int admin;

    public User(int userId, String username, String password, int admin) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.admin = admin;
    }

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public static User lookupUser(int userId) throws SQLException {

        User listedUser = null;

        ObservableList<User> allUsers = UserQuery.getAllUsers();

        for(User user : allUsers) {
            if (user.userId == userId){
                listedUser = user;
            }
        }
        return listedUser;
    }

    public static ObservableList<User> lookupUser (String username) throws SQLException {
        ObservableList<User> listedUser = FXCollections.observableArrayList();
        ObservableList<User> allUsers = UserQuery.getAllUsers();

        for(User user : allUsers) {
            if (user.getUsername().toLowerCase().contains(username)) {
                listedUser.add(user);
            }
        }
        return  listedUser;
    }
}
