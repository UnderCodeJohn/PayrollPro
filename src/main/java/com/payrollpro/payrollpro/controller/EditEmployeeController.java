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

public class EditEmployeeController implements Initializable, ViewChangeHelper, ErrorMessage {
    public AnchorPane rootPane;
    public VBox containerPane;
    public VBox editEmployeePane;
    public Label employeeIdLabel;
    public Label nameLabel;
    public Label addressLabel;
    public Label postalLabel;
    public Label phoneLabel;
    public Label emailLabel;
    public Label jobTitleLabel;
    public TextField employeeIdField;
    public TextField employeeNameField;
    public TextField addressField;
    public TextField postalCodeField;
    public TextField emailField;
    public TextField phoneField;
    public TextField jobTitleField;
    public Label nameError;
    public Label addressError;
    public Label postalError;
    public Label phoneError;
    public Label emailError;
    public Label jobTitleError;
    public Employee SEmployee = new ManageEmployeeController().passEmployee();

    public EditEmployeeController() throws SQLException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        editEmployeePane.getStyleClass().add("main_pane");
        centerView(containerPane);

        employeeIdField.setText(Integer.toString(SEmployee.getEmployeeId()));
        employeeNameField.setText(SEmployee.getName());
        addressField.setText(SEmployee.getName());
        postalCodeField.setText(SEmployee.getPostalCode());
        phoneField.setText(SEmployee.getPhone());
        emailField.setText(SEmployee.getEmail());
        jobTitleField.setText(SEmployee.getJobTitle());
    }

    public void saveEmployee(ActionEvent actionEvent) throws IOException, SQLException {
        String[] fields = {employeeNameField.getText(), addressField.getText(), postalCodeField.getText(), phoneField.getText(), emailField.getText(), jobTitleField.getText()};
        TextField[] textFields = {employeeNameField, addressField, postalCodeField, phoneField, emailField, jobTitleField};
        Label[] labels = {nameLabel, addressLabel, postalLabel, phoneLabel, emailLabel, jobTitleLabel};
        Label[] errorLabels = {nameError, addressError, postalError, phoneError, emailError, jobTitleError};

        boolean isValid = isErrorMessage(fields, textFields, labels, errorLabels);
        if (isValid) {
            Employee newEmployee = new Employee(SEmployee.getEmployeeId(), employeeNameField.getText(), addressField.getText(), postalCodeField.getText(), phoneField.getText(), emailField.getText(), jobTitleField.getText(),-1, false);
            EmployeeQuery.updateEmployee(newEmployee);
            changeViewAndCenter("manage-employee-view.fxml",rootPane);
        }
    }

    public void cancel(ActionEvent actionEvent) throws IOException {
        changeViewAndCenter("manage-employee-view.fxml",rootPane);
    }
}
