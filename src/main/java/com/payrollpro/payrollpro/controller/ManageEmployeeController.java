package com.payrollpro.payrollpro.controller;

import com.payrollpro.payrollpro.Interface.ViewChangeHelper;
import com.payrollpro.payrollpro.model.Employee;
import com.payrollpro.payrollpro.utils.EmployeeQuery;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ManageEmployeeController implements Initializable, ViewChangeHelper {
    public AnchorPane rootPane;
    public VBox containerPane;
    public VBox employeePane;
    public TableView employeeTable;
    public TableColumn idCol;
    public TableColumn nameCol;
    public TableColumn addressCol;
    public TableColumn postalCodeCol;
    public TableColumn phoneCol;
    public TableColumn emailCol;
    public TableColumn jobTitleCol;
    public TableColumn userIdCol;
    public ObservableList <Employee> allEmployees = EmployeeQuery.getAllEmployees();
    public static Employee SEmployee;

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
    }
    public void addEmployee(ActionEvent actionEvent) throws IOException {
        changeViewAndCenter("add-employee-view.fxml",rootPane);
    }

    public void editEmployee(ActionEvent actionEvent) throws IOException {

        SEmployee = (Employee) employeeTable.getSelectionModel().getSelectedItem();
        if(SEmployee == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No Employee selected");
            alert.showAndWait();
        } else {
            changeViewAndCenter("edit-employee-view.fxml",rootPane);
        }
    }

    public void deleteEmployee(ActionEvent actionEvent) {
    }

    public void backToHome(ActionEvent actionEvent) throws IOException {
        changeViewAndCenter("admin-view.fxml",rootPane);
    }

    public Employee passEmployee(){return SEmployee;}
}
