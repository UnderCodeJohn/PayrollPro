package com.payrollpro.payrollpro.Interface;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Interface for handling error messages in form validation.
 */
public interface ErrorMessage {

    /**
     * Checks if any of the fields are empty and displays error messages accordingly.
     *
     * @param fields       An array of field values to check
     * @param textFields   An array of TextFields corresponding to the fields array
     * @param labels       An array of Labels corresponding to the fields array
     * @param errorLabels  An array of Labels to display error messages
     * @return true if all fields have values and no errors, false otherwise
     */
    default boolean isErrorMessage(String[] fields, TextField[] textFields, Label[] labels, Label[] errorLabels) {

        boolean hasError = false;

        for (int i = 0; i < fields.length; i++) {
            if (fields[i].trim().isEmpty()) {
                textFields[i].setStyle("-fx-border-color: red");
                errorLabels[i].setText(labels[i].getText() + " is required");
                hasError = true;
            } else {
                textFields[i].setStyle("-fx-border-color: green");
                errorLabels[i].setText("");
            }
        }
        return !hasError;
    }
}
