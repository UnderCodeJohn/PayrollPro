package com.payrollpro.payrollpro.controller;

import com.payrollpro.payrollpro.Interface.ViewChangeHelper;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable , ViewChangeHelper {
    public AnchorPane rootPane;
    public VBox containerPane;
    public VBox adminPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Set the login pane's style class for styling purposes
        adminPane.getStyleClass().add("main_pane");

        // Set the container pane's anchor properties to fill the parent anchor pane
        centerView(containerPane);
    }
    public void manageEmployees(ActionEvent actionEvent) throws IOException {
        changeViewAndCenter("manage-employee-view.fxml",rootPane);
    }
    public void manageAccounts(ActionEvent actionEvent) {
    }
    public void managePayroll(ActionEvent actionEvent) {
    }
    public void generateReports(ActionEvent actionEvent) {
    }
    public void logout(ActionEvent actionEvent) {
    }
}
