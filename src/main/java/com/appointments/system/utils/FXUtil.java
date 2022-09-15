package com.appointments.system.utils;

import com.appointments.system.controller.DashboardController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class FXUtil {

    public static final String CUSTOMER_PANEL = "customers_panel.fxml";
    public static final String CUSTOMER_ADD = "customers_add.fxml";
    public static final String CUSTOMER_VIEW = "customers_view.fxml";
    public static final String APPOINTMENTS_PANEL = "appointments_panel.fxml";
    public static String LOGIN = "app_login.fxml";
    public static String DASHBOARD = "dashboard.fxml";
    public static String APPOINTMENTS_ADD = "appointments_add.fxml";
    public static String APPOINTMENTS_HOME = "appointments_home.fxml";
    public static String APPOINTMENTS_VIEW = "appointments_view.fxml";

    // load fxml view in the stage
    public static void loadAnchorView(Class<?> aClass, String fxSource, AnchorPane rootPane, Object... data) {
        try {
            // load view in anchor pane area and control separately
            FXMLLoader loader = new FXMLLoader(aClass.getResource(fxSource));
            AnchorPane pane = loader.load();
            // transfer data to the controller
            if (data.length > 0) {
                DataTraveler controller = loader.getController();
                controller.data(data);
            }
            rootPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

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
