package com.payrollpro.payrollpro;

import com.payrollpro.payrollpro.utils.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The Launcher class is responsible for launching the Payroll Pro application.
 */
public class Launcher extends Application {

    /**
     * The entry point of the application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        JDBC.openConnection();
        JDBC.closeConnection();
        launch();
        System.out.println("Hello");
    }

    /**
     * Starts the application and initializes the main stage.
     *
     * @param stage The primary stage of the application.
     * @throws IOException if an error occurs while loading the FXML file.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        stage.setTitle("Payroll Pro");
        stage.setScene(scene);
        stage.show();
    }
}
