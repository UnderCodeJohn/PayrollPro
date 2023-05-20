package com.payrollpro.payrollpro.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class NavBarController {

    @FXML
    private Button homeButton;

    @FXML
    private Button profileButton;

    // Define event handlers for navigation buttons
    public void handleHomeButton() {
        // Handle home button action
        // You can change the view or perform any other necessary actions
        System.out.println("Home button clicked");
    }

    public void handleProfileButton() {
        // Handle profile button action
        // You can change the view or perform any other necessary actions
        System.out.println("Profile button clicked");
    }

    // Add event handlers for other navigation buttons as needed
}
