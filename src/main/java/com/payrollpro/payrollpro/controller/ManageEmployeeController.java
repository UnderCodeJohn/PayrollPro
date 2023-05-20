package com.payrollpro.payrollpro.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageEmployeeController implements Initializable {
    public VBox rootPane;
    public VBox containerPane;
    public VBox employeePane;
    public TableView employeeTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        employeePane.getStyleClass().add("login_pane");
    }
    public void addEmployee(ActionEvent actionEvent) {
    }

    public void editEmployee(ActionEvent actionEvent) {
    }

    public void deleteEmployee(ActionEvent actionEvent) {
    }

    public void backToHome(ActionEvent actionEvent) {
    }
}
