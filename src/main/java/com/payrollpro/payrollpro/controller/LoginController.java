package com.payrollpro.payrollpro.controller;

import com.payrollpro.payrollpro.Interface.ViewChangeHelper;
import com.payrollpro.payrollpro.utils.UserLoginQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * The LoginController class handles the initialization and styling of the login page.
 */
public class LoginController implements Initializable , ViewChangeHelper {
    @FXML
    public AnchorPane rootPane;
    @FXML
    public VBox loginPane;
    @FXML
    public VBox containerPane;
    public PasswordField passwordField;
    public TextField usernameField;
    public Text loginErrorMessage;

    /**
     * Initializes the login page and sets the UI elements' alignment to center.
     *
     * @param url            The location used to resolve relative paths.
     * @param resourceBundle The resource bundle containing localized resources.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set the login pane's style class for styling purposes
        loginPane.getStyleClass().add("main_pane");

        // Set the container pane's anchor properties to fill the parent anchor pane
        centerView(containerPane);
    }

    public void onLogin(ActionEvent actionEvent) throws SQLException, IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        boolean loginSuccessful;

        loginSuccessful = UserLoginQuery.login(username,password);

        if (loginSuccessful) {
            System.out.println("Login Successful");
            changeViewAndCenter("admin-view.fxml",rootPane);

        } else {
            System.out.println("Login Failed");
            loginErrorMessage.setVisible(true);
            loginErrorMessage.setText("Invalid username or password");
            usernameField.clear();
            passwordField.clear();
            usernameField.requestFocus();
        }
    }
}
