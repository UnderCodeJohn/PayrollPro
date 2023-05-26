package com.payrollpro.payrollpro.Interface;

import com.payrollpro.payrollpro.model.Employee;
import com.payrollpro.payrollpro.model.User;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public interface AccountTableBehavior {
    default void applyRowFactory(TableView<User> userTable, TextField searchField) {
        userTable.setRowFactory(tv -> {
            TableRow<User> row = new TableRow<>();
            row.itemProperty().addListener((obs, previousUser, currentUser) -> {
                if (currentUser != null) {
                    // Get the userID value of the current user
                    int isAdmin = currentUser.getAdmin();

                    // Change the row color based on the userID value
                    if (isAdmin == 0) {
                        row.setStyle("-fx-background-color: rgba(0, 0, 255, 0.1);");
                    } else {
                        row.setStyle("-fx-background-color: rgba(0, 255, 0, 0.1);");
                    }
                } else {
                    // Reset the row style to default when the row becomes empty
                    row.setStyle("");
                }
            });

            // Apply the row selection color with increased transparency
            row.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
                if (isSelected) {
                    row.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
                } else {
                    // Reapply the original row color based on the userID value
                    User currentUser = row.getItem();
                    if (currentUser != null) {
                        int isAdmin = currentUser.getAdmin();
                        if (isAdmin == 0) {
                            row.setStyle("-fx-background-color: rgba(0, 0, 255, 0.1);");
                        } else {
                            row.setStyle("-fx-background-color: rgba(0, 255, 0, 0.1);");
                        }
                    }
                }
            });

            return row;
        });

        // Listener for table focus changes
        userTable.focusedProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue) {
                // Reset the row styles to the original color when the table loses focus
                for (User user : userTable.getItems()) {
                    TableRow<User> row = userTable.getRowFactory().call(userTable);
                    int isAdmin = user.getAdmin();
                    if (isAdmin == 0) {
                        row.setStyle("-fx-background-color: rgba(0, 0, 255, 0.1);");
                    } else {
                        row.setStyle("-fx-background-color: rgba(0, 255, 0, 0.1);");
                    }
                }
            }
        });

        // Listener for text field focus changes
        searchField.focusedProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                // Change the row color to green or red when the text field is focused
                userTable.getSelectionModel().clearSelection();

                for (User user : userTable.getItems()) {
                    TableRow<User> row = userTable.getRowFactory().call(userTable);
                    int isAdmin = user.getAdmin();
                    if (isAdmin == 0) {
                        row.setStyle("-fx-background-color: rgba(0, 0, 255, 0.1);");
                    } else {
                        row.setStyle("-fx-background-color: rgba(0, 255, 0, 0.1);");
                    }
                }
            } else {
                // Reset the row styles to the original color when the text field loses focus
                for (User user : userTable.getItems()) {
                    TableRow<User> row = userTable.getRowFactory().call(userTable);
                    int isAdmin = user.getAdmin();
                    if (isAdmin == 0) {
                        row.setStyle("-fx-background-color: rgba(0, 0, 255, 0.1);");
                    } else {
                        row.setStyle("-fx-background-color: rgba(0, 255, 0, 0.1);");
                    }
                }
            }
        });
    }

    default void clearRowStyles(TableView<Employee> employeeTable) {
        for (int i = 0; i < employeeTable.getItems().size(); i++) {
            TableRow<Employee> row = employeeTable.getRowFactory().call(employeeTable);
            Employee employee = employeeTable.getItems().get(i);

            if (employee != null) {
                int userID = employee.getUserId();
                if (userID == 0) {
                    row.setStyle("-fx-background-color: rgba(255, 0, 0, 0.1);");
                } else {
                    row.setStyle("-fx-background-color: rgba(0, 255, 0, 0.1);");
                }
            } else {
                // Reset the style for empty rows
                row.setStyle("");
            }
        }
    }
}


