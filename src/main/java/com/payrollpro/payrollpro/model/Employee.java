package com.payrollpro.payrollpro.model;

import com.payrollpro.payrollpro.utils.EmployeeQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class Employee {
    private int employeeId;
    private String name;
    private String address;
    private String postalCode;
    private String phone;
    private String email;
    private String jobTitle;
    private int userId;

    public Employee(int employeeId, String name, String address, String postalCode, String phone, String email, String jobTitle, int userId) {
        this.employeeId = employeeId;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.email = email;
        this.jobTitle = jobTitle;
        this.userId = userId;
    }

    public Employee() {
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public static Employee lookupEmployee(int employeeId) throws SQLException {

        Employee listedEmployee = null;

        ObservableList<Employee> allEmployees = EmployeeQuery.getAllEmployees();

        for(Employee employee : allEmployees) {
            if (employee.employeeId == employeeId){
                listedEmployee = employee;
            }
        }
        return listedEmployee;
    }

    public static ObservableList<Employee> lookupEmployee (String employeeName) throws SQLException {
        ObservableList<Employee> listedEmployee = FXCollections.observableArrayList();
        ObservableList<Employee> allEmployees = EmployeeQuery.getAllEmployees();

        for(Employee employee : allEmployees) {
            if (employee.getName().toLowerCase().contains(employeeName)) {
                listedEmployee.add(employee);
            }
        }
        return  listedEmployee;
    }
}
