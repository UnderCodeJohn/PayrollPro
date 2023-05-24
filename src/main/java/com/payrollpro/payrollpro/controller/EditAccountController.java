package com.payrollpro.payrollpro.controller;

import com.payrollpro.payrollpro.Interface.ErrorMessage;
import com.payrollpro.payrollpro.Interface.ViewChangeHelper;
import com.payrollpro.payrollpro.model.Employee;
import com.payrollpro.payrollpro.model.User;
import com.payrollpro.payrollpro.utils.UserQuery;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditAccountController implements Initializable, ViewChangeHelper, ErrorMessage {
    public AnchorPane rootPane;
    public VBox containerPane;
    public VBox editAccountPane;
    public Label accountIdLabel;
    public Label usernameLabel;
    public Label passwordLabel;
    public TextField accountIdField;
    public TextField usernameField;
    public TextField passwordField;
    public Label usernameError;
    public Label passwordError;
    public User SUser = new ManageAccountController().passUser();

    public EditAccountController() throws SQLException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        editAccountPane.getStyleClass().add("main_pane");
        centerView(containerPane);

        accountIdField.setText(Integer.toString(SUser.getUserId()));
        usernameField.setText(SUser.getUsername());
        passwordField.setText(SUser.getPassword());
    }

    public void saveAccount(ActionEvent actionEvent) throws IOException, SQLException {
        String[] fields = {usernameField.getText(), passwordField.getText()};
        TextField[] textFields = {usernameField, passwordField};
        Label[] labels = {usernameLabel, passwordLabel};
        Label[] errorLabels = {usernameError, passwordError};

        boolean isValid = isErrorMessage(fields, textFields, labels, errorLabels);

        if (isValid) {
            User newUser = new User(SUser.getUserId(), usernameField.getText(), passwordField.getText(), -1);
            UserQuery.updateUser(newUser);
            changeViewAndCenter("manage-account-view.fxml",rootPane);
        }
    }

    public void cancel(ActionEvent actionEvent) throws IOException {
        changeViewAndCenter("manage-account-view.fxml",rootPane);
    }
}
