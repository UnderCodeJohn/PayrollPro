package com.payrollpro.payrollpro.utils;

import com.payrollpro.payrollpro.model.Employee;
import com.payrollpro.payrollpro.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class EmployeeQuery {

    public static ObservableList <Employee> getAllEmployees() throws SQLException {
        ObservableList<Employee> returnAllEmployees = FXCollections.observableArrayList();
        String sql = "SELECT * FROM employees";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int employeeID = rs.getInt("Employee_ID");
            String employeeName = rs.getString("Employee_Name");
            String address = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            String email = rs.getString("Email");
            String jobTitle = rs.getString("Job_Title");
            int userID = rs.getInt("User_ID");

            Employee newEmployee = new Employee(employeeID, employeeName, address, postalCode, phone, email, jobTitle, userID);
            returnAllEmployees.add(newEmployee);
        }
        return returnAllEmployees;
    }

    public static void addEmployee(Employee employee, User user) throws SQLException {
        String insertUserSql = "INSERT INTO users (User_Name, Password, Admin)\n" +
                "VALUES (?, ?, 0);";
        String setLastUserIdSql = "SET @last_user_id = LAST_INSERT_ID();";
        String insertEmployeeSql = "INSERT INTO employees (Employee_name, Address, Postal_Code, Phone, Email, Job_Title, User_ID)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, @last_user_id);";

        // Insert User
        PreparedStatement insertUserPs = JDBC.connection.prepareStatement(insertUserSql);
        insertUserPs.setString(1, user.getUsername());
        insertUserPs.setString(2, user.getPassword());
        insertUserPs.executeUpdate();

        // Set @last_user_id
        PreparedStatement setLastUserIdPs = JDBC.connection.prepareStatement(setLastUserIdSql);
        setLastUserIdPs.executeUpdate();

        // Insert Employee
        PreparedStatement insertEmployeePs = JDBC.connection.prepareStatement(insertEmployeeSql);
        insertEmployeePs.setString(1, employee.getName());
        insertEmployeePs.setString(2, employee.getAddress());
        insertEmployeePs.setString(3, employee.getPostalCode());
        insertEmployeePs.setString(4, employee.getPhone());
        insertEmployeePs.setString(5, employee.getEmail());
        insertEmployeePs.setString(6, employee.getJobTitle());
        insertEmployeePs.executeUpdate();
    }

}
