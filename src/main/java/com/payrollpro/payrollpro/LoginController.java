package com.payrollpro.payrollpro;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * The LoginController class handles the initialization and styling of the login page.
 */
public class LoginController implements Initializable {

    @FXML
    public AnchorPane rootPane;

    @FXML
    public VBox loginPane;

    @FXML
    public VBox containerPane;

    /**
     * Initializes the login page and sets the UI elements' alignment to center.
     *
     * @param url            The location used to resolve relative paths.
     * @param resourceBundle The resource bundle containing localized resources.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set the login pane's style class for styling purposes
        loginPane.getStyleClass().add("login_pane");

        // Set the container pane's anchor properties to fill the parent anchor pane
        AnchorPane.setTopAnchor(containerPane, 0.0);
        AnchorPane.setLeftAnchor(containerPane, 0.0);
        AnchorPane.setRightAnchor(containerPane, 0.0);
        AnchorPane.setBottomAnchor(containerPane, 0.0);

        // Center the login pane within the root pane
        double centerOffsetX = (rootPane.getWidth() - loginPane.getWidth()) / 2;
        double centerOffsetY = (rootPane.getHeight() - loginPane.getHeight()) / 2;
        AnchorPane.setTopAnchor(loginPane, centerOffsetY);
        AnchorPane.setLeftAnchor(loginPane, centerOffsetX);
    }
}
