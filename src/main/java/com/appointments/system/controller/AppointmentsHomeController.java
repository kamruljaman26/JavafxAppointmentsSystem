package com.appointments.system.controller;

import com.appointments.system.model.Customers;
import com.appointments.system.repo.CustomerDao;
import com.appointments.system.utils.DataTraveler;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AppointmentsHomeController implements Initializable, DataTraveler {

    // fx ids
    public TableView<Customers> tableViewID;
    public Label titleLabelID;

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
