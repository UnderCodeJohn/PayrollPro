package com.payrollpro.payrollpro.model;

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
}
