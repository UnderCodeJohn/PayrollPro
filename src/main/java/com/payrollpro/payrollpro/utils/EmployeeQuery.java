package com.payrollpro.payrollpro.utils;

import com.payrollpro.payrollpro.Enum.ClockEntryType;
import com.payrollpro.payrollpro.model.ClockEntry;
import com.payrollpro.payrollpro.model.Employee;
import com.payrollpro.payrollpro.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
            boolean clockedIn = rs.getBoolean("Clocked_In");

            Employee newEmployee = new Employee(employeeID, employeeName, address, postalCode, phone, email, jobTitle, userID, clockedIn);
            returnAllEmployees.add(newEmployee);
        }
        return returnAllEmployees;
    }

    public static void addEmployee(Employee employee, User user) throws SQLException {
        String insertUserSql = "INSERT INTO users (Username, Password, Admin)\n" +
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

    public static void updateEmployee(Employee employee) throws SQLException {
        String sql = "UPDATE employees SET Employee_name = ?, Address = ?, Postal_Code = ?, Phone = ?, Email = ?, Job_Title = ? WHERE Employee_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, employee.getName());
        ps.setString(2, employee.getAddress());
        ps.setString(3, employee.getPostalCode());
        ps.setString(4, employee.getPhone());
        ps.setString(5, employee.getEmail());
        ps.setString(6, employee.getJobTitle());
        ps.setInt(7, employee.getEmployeeId());

        ps.executeUpdate();
    }

    public static void deleteEmployee(Employee employee) throws SQLException {
        // Update the foreign key column in the employees table
        String updateSQL = "UPDATE employees SET User_ID = NULL WHERE Employee_ID = ?";

        // Delete the user from the users table
        String deleteSQL = "DELETE FROM users WHERE User_ID = ?";

        try (PreparedStatement updatePs = JDBC.connection.prepareStatement(updateSQL)) {
            updatePs.setInt(1, employee.getEmployeeId());
            updatePs.executeUpdate();
        } catch (SQLException e) {
            // Handle specific exception for the update statement
            System.out.println("Error executing update statement: " + e.getMessage());
        }

        try (PreparedStatement deletePs = JDBC.connection.prepareStatement(deleteSQL)) {
            deletePs.setInt(1, employee.getUserId());
            deletePs.executeUpdate();
        } catch (SQLException e) {
            // Handle specific exception for the delete statement
            System.out.println("Error executing delete statement: " + e.getMessage());
        }
    }
    public static void updateEmployeeClockedInStatus(User user, boolean clockedIn) throws SQLException {
        String sql = "UPDATE employees SET CLOCKED_IN = ? WHERE USER_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setBoolean(1, clockedIn);
        ps.setInt(2, user.getUserId());
        ps.executeUpdate();
    }

    public static Employee getEmployee(User user) throws SQLException {
        String sql = "SELECT * FROM employees WHERE USER_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, user.getUserId());
        ResultSet rs = ps.executeQuery();
        Employee newEmployee = new Employee();
        if (rs.next()) {
            int employeeID = rs.getInt("Employee_ID");
            String employeeName = rs.getString("Employee_Name");
            String address = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            String email = rs.getString("Email");
            String jobTitle = rs.getString("Job_Title");
            int userID = rs.getInt("User_ID");
            boolean clockedIn = rs.getBoolean("Clocked_In");

            newEmployee = new Employee(employeeID, employeeName, address, postalCode, phone, email, jobTitle, userID, clockedIn);
        }

        return newEmployee;
    }

    public static void insertClockedInEntry(ClockEntry clockEntry) throws SQLException {
        String sql = "INSERT INTO clocked_entries (EMPLOYEE_ID, TIMESTAMP, ENTRY_TYPE) VALUES (?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, clockEntry.getEmployeeId());
        ps.setObject(2, clockEntry.getTimestamp());
        ps.setString(3, clockEntry.getEntryType().toString());
        ps.executeUpdate();
    }

    public static ObservableList<ClockEntry> getClockedEntries(Employee employee) throws SQLException {
        ObservableList<ClockEntry> returnAllEntries = FXCollections.observableArrayList();
        String sql = "SELECT * FROM clocked_entries";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int clockedEntryId = rs.getInt("Clocked_ID");
            int employeeId = rs.getInt("Employee_ID");
            LocalDate date = rs.getDate("timestamp").toLocalDate();
            LocalTime time = rs.getTime("timestamp").toLocalTime();
            ClockEntryType entryType = ClockEntryType.valueOf(rs.getString("entry_type"));

            LocalDateTime LDT = LocalDateTime.of(date, time);

            ClockEntry clockEntry = new ClockEntry(clockedEntryId, employeeId, LDT, entryType );
            returnAllEntries.add(clockEntry);
        }
        return returnAllEntries;
    }


}
