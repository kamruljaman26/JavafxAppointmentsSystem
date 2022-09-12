package com.appointments.system.utils;

import com.appointments.system.controller.DashboardController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.net.URL;

public class FXUtil {

    // all fxml view file links/location
    public static String LOGIN = "app_login.fxml";
    public static String DASHBOARD = "dashboard.fxml";

    // load fxml view in the stage
    public static void loadView(Class<?> aClass, ActionEvent event, String fxSource, String title, Object... data) {
        try {
            // load fxml
            FXMLLoader loader = new FXMLLoader(aClass.getResource(fxSource));
            Parent layout = loader.load();

            // transfer data to the controller
            if (data.length > 0) {
                DataTraveler controller = loader.getController();
                controller.data(data);
            }

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(layout);
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // show alert
    public static void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
