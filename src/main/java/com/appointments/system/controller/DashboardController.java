package com.appointments.system.controller;

import com.appointments.system.utils.DataTraveler;
import com.appointments.system.utils.FXUtil;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Main dashboard layout layout for user
 */
public class DashboardController implements Initializable, DataTraveler {

    // fxml ids
    public Label labelD;
    public Button customersMenuBtnID;
    public Button appointmentsMenuBtnID;
    public Button logoutMenuBtnID;
    public Label welcomeMsgLabelID;
    public AnchorPane mainAnchorPaneID;
    public ImageView logoImgViewID;

    // properties
    Object[] data;

    @Override
    public void data(Object... o) {
        this.data = o;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // init logo img
        Image image = new Image(String.valueOf(getClass().getResource("images/logo.png")));
        logoImgViewID.setImage(image);

        // set all Button actions
        customersMenuBtnID.setOnAction(this::customerMenuBtnAction);
        appointmentsMenuBtnID.setOnAction(this::appointmentsMenuBtnAction);
        logoutMenuBtnID.setOnAction(this::logoutBtnAction);

        // initially load customer
        FXUtil.loadAnchorView(getClass(),FXUtil.CUSTOMER_PANEL, mainAnchorPaneID); // replace view
    }

    // load customer panel
    private void customerMenuBtnAction(ActionEvent event){
        FXUtil.loadAnchorView(getClass(),FXUtil.CUSTOMER_PANEL, mainAnchorPaneID); // replace view
    }

    // load appointments panel
    private void appointmentsMenuBtnAction(ActionEvent event){
        FXUtil.loadAnchorView(getClass(),FXUtil.APPOINTMENTS_PANEL, mainAnchorPaneID); // replace view
    }

    // logout from dashboard
    private void logoutBtnAction(ActionEvent event){
        // load new view
        ((Node) event.getSource()).getScene().getWindow().hide(); // hide view
        FXUtil.loadView(getClass(), event, FXUtil.LOGIN, "User Login");
    }

}
