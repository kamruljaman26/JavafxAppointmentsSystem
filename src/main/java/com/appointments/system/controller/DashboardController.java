package com.appointments.system.controller;

import com.appointments.system.model.*;
import com.appointments.system.repo.*;
import com.appointments.system.utils.DataTraveler;
import com.appointments.system.utils.LanguageUtil;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;


public class DashboardController implements Initializable, DataTraveler {

    public Label labelD;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void data(Object... o) {
        if(o.length>0){
            Users users = (Users) o[0];
            labelD.setText(users.getUserName());
        }
    }
}
