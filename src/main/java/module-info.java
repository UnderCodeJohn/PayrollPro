module com.payrollpro.payrollpro {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.payrollpro.payrollpro to javafx.fxml;
    exports com.payrollpro.payrollpro;
}