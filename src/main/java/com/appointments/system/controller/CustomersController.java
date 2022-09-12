package com.appointments.system.controller;

import com.appointments.system.utils.DataTraveler;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;


public class CustomersController implements Initializable, DataTraveler {

    // fxml ids

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
