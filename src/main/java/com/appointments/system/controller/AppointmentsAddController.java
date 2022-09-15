package com.appointments.system.controller;

import com.appointments.system.model.*;
import com.appointments.system.repo.AppointmentsDao;
import com.appointments.system.repo.CountriesDao;
import com.appointments.system.repo.CustomerDao;
import com.appointments.system.repo.FirstLevelDivisionsDao;
import com.appointments.system.utils.DataTraveler;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;


public class AppointmentsAddController implements Initializable, DataTraveler {


    @Override
    public void data(Object... o) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    // update customer button action
    private void updateAppointmentsButtonAction(ActionEvent event) {

    }

    // delete selected customer
    private void deleteAppointmentsBtnAction(ActionEvent event) {

    }

    // collect data from filed and create new customer
    private void addAppointmentsButtonAction(ActionEvent event) {

    }


    // clear all fields and current customer
    private void clearButtonAction(ActionEvent event) {

    }

    // handle find button action
    private void findAppointmentsButtonAction(ActionEvent event) {

    }
}
