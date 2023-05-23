package com.payrollpro.payrollpro.controller;

import com.payrollpro.payrollpro.Interface.AccountTableBehavior;
import com.payrollpro.payrollpro.Interface.ViewChangeHelper;
import com.payrollpro.payrollpro.model.User;
import com.payrollpro.payrollpro.utils.UserQuery;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ManageAccountController implements Initializable , ViewChangeHelper, AccountTableBehavior {
    public AnchorPane rootPane;
    public VBox containerPane;
    public VBox accountPane;
    public TextField searchField;
    public TableView<User> accountTable;
    public TableColumn<Object, Object> idCol;
    public TableColumn<Object, Object> usernameCol;
    public TableColumn<Object, Object> passwordCol;
    public ObservableList<User> allUsers = UserQuery.getAllUsers();
    public TableColumn<Object, Object> adminCol;
    public Text noAccountFound;

    public ManageAccountController() throws SQLException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        accountPane.getStyleClass().add("main_pane");
        centerView(containerPane);

        accountTable.setItems(allUsers);
        idCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        adminCol.setCellValueFactory(new PropertyValueFactory<>("admin"));

        applyRowFactory(accountTable, searchField);
    }

    public void userSearch(KeyEvent keyEvent) throws SQLException {

        String userSearch = searchField.getText().toLowerCase();
        ObservableList<User> listedEmployees = User.lookupUser(userSearch);
        try {
            int employeeSearchId = Integer.parseInt(searchField.getText());
            User listedEmployeeId = User.lookupUser(employeeSearchId);
            if (listedEmployeeId == null) {
                noAccountFound.setText("No Account Found");
                accountTable.setItems(null);
            } else {
                listedEmployees.add(listedEmployeeId);
                accountTable.setItems(listedEmployees);
                noAccountFound.setText("No Account Found");
            }
        } catch (NumberFormatException | SQLException e) {
            if (listedEmployees.isEmpty()) {
                noAccountFound.setText("No Account Found");
                accountTable.setItems(null);
            } else {
                accountTable.setItems(listedEmployees);
                noAccountFound.setText("No Account Found");
            }
        }
    }

    public void addAdmin(ActionEvent actionEvent) throws IOException {
        changeViewAndCenter("add-account-view.fxml",rootPane);
    }

    public void editAccount(ActionEvent actionEvent) {
    }

    public void deleteAccount(ActionEvent actionEvent) {
    }

    public void backToHome(ActionEvent actionEvent) throws IOException {
        changeViewAndCenter("admin-view.fxml",rootPane);
    }
}
