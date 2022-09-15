package com.appointments.system.controller;

import com.appointments.system.utils.DataTraveler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;


public class AppointmentsController implements Initializable, DataTraveler {

    // fxml ids
    public Button viewBtnID;
    public Button addBtnID;
    public AnchorPane mainAnchorPaneID;

    // properties
    Object[] data;

    @Override
    public void data(Object... o) {
        this.data = o;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
