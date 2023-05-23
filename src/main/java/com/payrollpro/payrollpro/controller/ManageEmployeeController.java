package com.payrollpro.payrollpro.controller;

import com.payrollpro.payrollpro.Interface.EmployeeTableBehavior;
import com.payrollpro.payrollpro.Interface.ViewChangeHelper;
import com.payrollpro.payrollpro.model.Employee;
import com.payrollpro.payrollpro.utils.EmployeeQuery;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class ManageEmployeeController implements Initializable, ViewChangeHelper, EmployeeTableBehavior {
    public AnchorPane rootPane;
    public VBox containerPane;
    public VBox employeePane;
    public TableView<Employee> employeeTable;
    public TableColumn<Object, Object> idCol;
    public TableColumn<Object, Object> nameCol;
    public TableColumn<Object, Object> addressCol;
    public TableColumn<Object, Object> postalCodeCol;
    public TableColumn<Object, Object> phoneCol;
    public TableColumn<Object, Object> emailCol;
    public TableColumn<Object, Object> jobTitleCol;
    public TableColumn<Object, Object> userIdCol;
    public ObservableList <Employee> allEmployees = EmployeeQuery.getAllEmployees();
    public static Employee SEmployee;
    public TextField searchField;

    public ManageEmployeeController() throws SQLException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        employeePane.getStyleClass().add("main_pane");
        centerView(containerPane);

        employeeTable.setItems(allEmployees);
        idCol.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        jobTitleCol.setCellValueFactory(new PropertyValueFactory<>("jobTitle"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));

        applyRowFactory(employeeTable, searchField);
    }

    public void addEmployee(ActionEvent actionEvent) throws IOException {
        changeViewAndCenter("add-employee-view.fxml",rootPane);
    }

    public void editEmployee(ActionEvent actionEvent) throws IOException {

        SEmployee = employeeTable.getSelectionModel().getSelectedItem();
        if(SEmployee == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No Employee selected");
            alert.showAndWait();
        } else {
            changeViewAndCenter("edit-employee-view.fxml",rootPane);
        }
    }

    public void deleteEmployee(ActionEvent actionEvent) throws SQLException {
        SEmployee = employeeTable.getSelectionModel().getSelectedItem();
        if (SEmployee == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No Employee selected");
            alert.showAndWait();
        } else {
            if (SEmployee.getUserId() == 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Deactivated Employee");
                alert.setContentText("The selected employee account is already deactivated");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Delete Employee");
                alert.setContentText("Are you sure you want to deactivate the selected employee? \nThis action cannot be reversed.");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    EmployeeQuery.deleteEmployee(SEmployee);
                    refreshEmployeeTable();
                }
            }
        }
    }

    public void backToHome(ActionEvent actionEvent) throws IOException {
        changeViewAndCenter("admin-view.fxml",rootPane);
    }

    private void refreshEmployeeTable() throws SQLException {
        ObservableList<Employee> newEmployeeList = EmployeeQuery.getAllEmployees();
        employeeTable.setItems(newEmployeeList);
    }

    public Employee passEmployee(){return SEmployee;}

    public void employeeSearch(KeyEvent keyEvent) throws SQLException {
        String employeeSearch = searchField.getText().toLowerCase();
        ObservableList<Employee> listedEmployees = Employee.lookupEmployee(employeeSearch);
        try {
            int employeeSearchId = Integer.parseInt(searchField.getText());
            Employee listedEmployeeId = Employee.lookupEmployee(employeeSearchId);
            if (listedEmployeeId == null) {
                System.out.println("No Employee Found");
                employeeTable.setItems(null);
            } else {
                listedEmployees.add(listedEmployeeId);
                employeeTable.setItems(listedEmployees);
            }
        } catch (NumberFormatException | SQLException e) {
            if (listedEmployees.isEmpty()) {
                System.out.println("No Employee Found");
                employeeTable.setItems(null);
            } else {
                employeeTable.setItems(listedEmployees);
            }
        }
    }
}
