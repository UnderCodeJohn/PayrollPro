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
        adminPane.getStyleClass().add("login_pane");

        // Set the container pane's anchor properties to fill the parent anchor pane
        AnchorPane.setTopAnchor(containerPane, 0.0);
        AnchorPane.setLeftAnchor(containerPane, 0.0);
        AnchorPane.setRightAnchor(containerPane, 0.0);
        AnchorPane.setBottomAnchor(containerPane, 0.0);

        // Center the login pane within the root pane
//        double centerOffsetX = (rootPane.getWidth() - adminPane.getWidth()) / 2;
//        double centerOffsetY = (rootPane.getHeight() - adminPane.getHeight()) / 2;
//        AnchorPane.setTopAnchor(adminPane, centerOffsetY);
//        AnchorPane.setLeftAnchor(adminPane, centerOffsetX);
    }
    public void manageEmployees(ActionEvent actionEvent) throws IOException {
        changeViewAndCenter("manage-employee-view.fxml",rootPane);
    }

    public void managePayroll(ActionEvent actionEvent) {
    }

    public void generateReports(ActionEvent actionEvent) {
    }

    public void logout(ActionEvent actionEvent) {
    }
}
