module com.payrollpro.payrollpro {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    exports com.payrollpro.payrollpro.model;
    exports com.payrollpro.payrollpro.Enum;
    exports com.payrollpro.payrollpro.controller;
    opens com.payrollpro.payrollpro.controller to javafx.fxml;
}