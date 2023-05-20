package com.payrollpro.payrollpro.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class UserLoginQuery extends JDBC{
    public static boolean login(String username, String password) throws SQLException {
        String sql = "SELECT * FROM USERS";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        boolean login = false;
        while (rs.next()) {
            if (username.equals(rs.getString(2)) && password.equals(rs.getString(3))) {
                login = true;
            }
        }
        return login;
    }
}
