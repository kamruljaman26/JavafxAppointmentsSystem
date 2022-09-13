package com.appointments.system.controller;

import com.appointments.system.model.Countries;
import com.appointments.system.model.Customers;
import com.appointments.system.model.FirstLevelDivisions;
import com.appointments.system.repo.CountriesDao;
import com.appointments.system.repo.FirstLevelDivisionsDao;
import com.appointments.system.utils.DataTraveler;
import com.appointments.system.utils.FXUtil;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CustomersAddController implements Initializable, DataTraveler {

    // fx ides
    public Button searchBtnID;
    public TextField customerIDTxtFldID;
    public ComboBox<String> countryComboBxID;
    public ComboBox<String> divisionComboBxID;
    public TextField postcodeTxtFldID;
    public TextField addressTxtFldID;
    public TextField phoneTxtFldID;
    public TextField nameTxtFldID;
    public Button addBtnID;
    public Button updateBtnID;
    public Button deleteBtnID;

    // properties
    Object[] data;

    @Override
    public void data(Object... o) {
        this.data = o;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // database
        CountriesDao countriesDao = new CountriesDao();
        FirstLevelDivisionsDao divisionsDao = new FirstLevelDivisionsDao();

        // Create a combo box - country box
        List<String> countries = countriesDao.findAll()
                .stream()
                .map(Countries::getCountry)
                .collect(Collectors.toList());
        countryComboBxID.setItems(FXCollections.observableArrayList(countries));
        countryComboBxID.setValue(countries.get(0));
        countryComboBxID.setOnAction(e -> {

        });

        // Create a combo box - country box
        List<String> divisions = divisionsDao.findAll()
                .stream()
                .map(FirstLevelDivisions::getDivisions)
//                .filter(f -> f.equals())
                .collect(Collectors.toList());
        divisionComboBxID.setItems(FXCollections.observableArrayList(divisions));

    }
}
