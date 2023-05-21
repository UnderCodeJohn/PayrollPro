package com.payrollpro.payrollpro.controller;

import com.payrollpro.payrollpro.Interface.ViewChangeHelper;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManageEmployeeController implements Initializable, ViewChangeHelper {
    public AnchorPane rootPane;
    public VBox containerPane;
    public VBox employeePane;
    public TableView employeeTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        employeePane.getStyleClass().add("main_pane");

        centerView(containerPane);
    }
    public void addEmployee(ActionEvent actionEvent) throws IOException {
        changeViewAndCenter("add-employee-view.fxml",rootPane);
    }

    public void editEmployee(ActionEvent actionEvent) {
    }

    public void deleteEmployee(ActionEvent actionEvent) {
    }

    public void backToHome(ActionEvent actionEvent) {
    }
}
