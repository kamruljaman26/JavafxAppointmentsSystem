package com.appointments.system.controller;

import com.appointments.system.utils.DataTraveler;
import com.appointments.system.utils.FXUtil;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;


public class AppointmentsPanelController implements Initializable, DataTraveler {

    // fxml ids
    public Button viewBtnID;
    public Button addBtnID;
    public AnchorPane mainAnchorPaneID;
    public Button homeBtnID;

    // properties
    Object[] data;

    @Override
    public void data(Object... o) {
        this.data = o;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // initially load view customer
        FXUtil.loadAnchorView(getClass(), FXUtil.APPOINTMENTS_HOME, mainAnchorPaneID); // replace view

        // view button
        viewBtnID.setOnAction(event -> {
            FXUtil.loadAnchorView(getClass(), FXUtil.APPOINTMENTS_VIEW, mainAnchorPaneID); // replace view
        });

        // add button
        addBtnID.setOnAction(event -> {
            FXUtil.loadAnchorView(getClass(), FXUtil.APPOINTMENTS_ADD, mainAnchorPaneID); // replace view
        });

        // home button
        homeBtnID.setOnAction(event -> {
            FXUtil.loadAnchorView(getClass(), FXUtil.APPOINTMENTS_HOME, mainAnchorPaneID); // replace view
        });
    }
}
