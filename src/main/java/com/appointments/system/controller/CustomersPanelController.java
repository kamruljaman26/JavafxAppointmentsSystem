package com.appointments.system.controller;

import com.appointments.system.model.Users;
import com.appointments.system.utils.DataTraveler;
import com.appointments.system.utils.FXUtil;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomersPanelController implements Initializable, DataTraveler {

    // fxml ids
    public Button viewBtnID;
    public Button addBtnID;
    public AnchorPane mainAnchorPaneID;

    // properties
    private Users users;

    @Override
    public void data(Object... o) {
        users = (Users) o[0];
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // initially load view customer
        FXUtil.loadAnchorView(getClass(), FXUtil.CUSTOMER_VIEW, mainAnchorPaneID, users); // replace view

        // view button
        viewBtnID.setOnAction(event -> {
            FXUtil.loadAnchorView(getClass(), FXUtil.CUSTOMER_VIEW, mainAnchorPaneID, users); // replace view
        });

        // view button
        addBtnID.setOnAction(event -> {
            FXUtil.loadAnchorView(getClass(), FXUtil.CUSTOMER_ADD, mainAnchorPaneID, users); // replace view
        });
    }
}
