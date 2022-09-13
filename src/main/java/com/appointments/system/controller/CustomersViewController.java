package com.appointments.system.controller;

import com.appointments.system.utils.DataTraveler;
import com.appointments.system.utils.FXUtil;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomersViewController implements Initializable, DataTraveler {


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
