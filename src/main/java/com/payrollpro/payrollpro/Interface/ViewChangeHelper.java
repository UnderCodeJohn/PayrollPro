package com.payrollpro.payrollpro.Interface;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public interface ViewChangeHelper {

    default void changeViewAndCenter(String fxmlFileName, AnchorPane rootPane) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFileName));
        Parent newView = fxmlLoader.load();

        rootPane.getChildren().clear();
        rootPane.getChildren().add(newView);

        // Set the layout properties of the new view to fill the rootPane
        AnchorPane.setTopAnchor(newView, 0.0);
        AnchorPane.setLeftAnchor(newView, 0.0);
        AnchorPane.setRightAnchor(newView, 0.0);
        AnchorPane.setBottomAnchor(newView, 0.0);
    }

    default void centerView(VBox containerPane){
        AnchorPane.setTopAnchor(containerPane, 0.0);
        AnchorPane.setLeftAnchor(containerPane, 0.0);
        AnchorPane.setRightAnchor(containerPane, 0.0);
        AnchorPane.setBottomAnchor(containerPane, 0.0);
    }
}

