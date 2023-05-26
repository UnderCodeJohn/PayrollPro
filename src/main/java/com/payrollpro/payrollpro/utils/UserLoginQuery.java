package com.payrollpro.payrollpro.utils;

import com.payrollpro.payrollpro.model.User;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class UserLoginQuery extends JDBC{
    public static User login(String username, String password) throws SQLException {
        String sql = "SELECT * FROM USERS WHERE username = ? AND password = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        User user = null;
        if (rs.next()) {
            user = new User();
            user.setUserId(rs.getInt("user_id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setAdmin(rs.getInt("admin"));
        }
        return user;
    }
}
