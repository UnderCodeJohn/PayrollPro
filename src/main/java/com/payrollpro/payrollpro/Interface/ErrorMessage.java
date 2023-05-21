package com.payrollpro.payrollpro.Interface;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public interface ErrorMessage {
    default boolean isErrorMessage (String[] fields, TextField[] textFields, Label[] labels, Label[] errorLabels){

        boolean hasError = false;

        for (int i = 0; i < fields.length; i++) {
            if (fields[i].trim().isEmpty()) {
                textFields[i].setStyle("-fx-border-color: red");
                errorLabels[i].setText(labels[i].getText() + " is required");
                hasError = true;
            } else {
                textFields[i].setStyle("-fx-border-color: transparent");
                errorLabels[i].setText("");
            }
        }
        return !hasError;
    }
}
