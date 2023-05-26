package com.payrollpro.payrollpro.controller;

import com.payrollpro.payrollpro.Interface.ErrorMessage;
import com.payrollpro.payrollpro.Interface.ViewChangeHelper;
import com.payrollpro.payrollpro.model.Employee;
import com.payrollpro.payrollpro.model.User;
import com.payrollpro.payrollpro.utils.EmployeeQuery;
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

public class AddEmployeeController implements Initializable, ViewChangeHelper, ErrorMessage {
    public AnchorPane rootPane;
    public VBox containerPane;
    public VBox addEmployeePane;
    public Label employeeIdLabel;
    public Label nameLabel;
    public Label addressLabel;
    public Label postalLabel;
    public Label emailLabel;
    public Label jobTitleLabel;
    public Label usernameLabel;
    public Label passwordLabel;
    public TextField employeeIdField;
    public TextField employeeNameField;
    public TextField addressField;
    public TextField postalCodeField;
    public TextField emailField;
    public TextField jobTitleField;
    public TextField usernameField;
    public TextField passwordField;
    public Label nameError;
    public Label addressError;
    public Label postalError;
    public Label emailError;
    public Label jobTitleError;
    public Label usernameError;
    public Label passwordError;
    public Label phoneError;
    public TextField phoneField;
    public Label phoneLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addEmployeePane.getStyleClass().add("main_pane");
        centerView(containerPane);
    }

    public void addEmployee(ActionEvent actionEvent) throws SQLException, IOException {

        String[] fields = {employeeNameField.getText(), addressField.getText(), postalCodeField.getText(), phoneField.getText(), emailField.getText(), jobTitleField.getText(), usernameField.getText(), passwordField.getText()};
        TextField[] textFields = {employeeNameField, addressField, postalCodeField, phoneField, emailField, jobTitleField, usernameField, passwordField};
        Label[] labels = {nameLabel, addressLabel, postalLabel, phoneLabel, emailLabel, jobTitleLabel, usernameLabel, passwordLabel};
        Label[] errorLabels = {nameError, addressError, postalError, phoneError, emailError, jobTitleError, usernameError, passwordError};

        boolean isValid = isErrorMessage(fields, textFields, labels, errorLabels);

        if (isValid) {
            Employee newEmployee = new Employee(-1, employeeNameField.getText(), addressField.getText(), postalCodeField.getText(), phoneField.getText(), emailField.getText(), jobTitleField.getText(),-1, false);
            User newUser = new User(-1,usernameField.getText(), passwordField.getText(), -1);
            EmployeeQuery.addEmployee(newEmployee, newUser);
            changeViewAndCenter("manage-employee-view.fxml",rootPane);
        }
    }

    public void cancel(ActionEvent actionEvent) throws IOException {
        changeViewAndCenter("manage-employee-view.fxml",rootPane);
    }
}
