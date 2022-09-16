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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;
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
    public Label messageLabelID;
    public Button clearFldsBtnID;

    // properties
    private CountriesDao countriesDao;
    private CustomerDao customerDao;
    private AtomicReference<Countries> currentCountry;
    private FirstLevelDivisionsDao divisionsDao;
    private Customers currCustomer;
    private Users users;

    @Override
    public void data(Object... o) {
        users = (Users) o[0];
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // init
        countriesDao = new CountriesDao();
        customerDao = new CustomerDao();
        currentCountry = new AtomicReference<>(countriesDao.findOne(1));
        divisionsDao = new FirstLevelDivisionsDao();

        updateCountryComboBox();
        updateDivisionComboBox();

        // button action
        searchBtnID.setOnAction(this::findCustomerButtonAction);
        clearFldsBtnID.setOnAction(this::clearButtonAction);
        addBtnID.setOnAction(this::addCustomerButtonAction);
        deleteBtnID.setOnAction(this::deleteCustomerBtnAction);
        updateBtnID.setOnAction(this::updateCustomerButtonAction);
    }

    // update customer button action
    private void updateCustomerButtonAction(ActionEvent event) {

        String name = nameTxtFldID.getText(), phone = phoneTxtFldID.getText(),
                address = addressTxtFldID.getText(), postcode = postcodeTxtFldID.getText();
        FirstLevelDivisions divisions = divisionsDao.findAll().stream()
                .filter(d -> d.getDivisions().equals(divisionComboBxID.getValue()))
                .collect(Collectors.toList()).get(0);

        if (currCustomer.getId() != Integer.parseInt(customerIDTxtFldID.getText())) {
            messageLabelID.setText("Sorry you can't change customer id, try with original customer's id");
        } else if (name.isEmpty() || phone.isEmpty() || address.isEmpty() || postcode.isEmpty()) {
            messageLabelID.setText("Please fill all information to update a customer.");
        } else {
            // create customer
            Customers customers = customerDao.findOne(Integer.parseInt(customerIDTxtFldID.getText()));
            customers.setName(name);
            customers.setPhone(phone);
            customers.setAddress(address);
            customers.setPostalCode(postcode);
            customers.setDivisionID(divisions);
            customers.setLasUpdate(LocalDateTime.now());
            customers.setLastUpdateBy(users.getUserName());

            customers = customerDao.update(customers);
            currCustomer = customers;
            customerIDTxtFldID.setText(customers.getId() + "");

            messageLabelID.setText("Customer updated successfully.");
        }
    }

    // delete selected customer
    private void deleteCustomerBtnAction(ActionEvent event) {
        if (currCustomer == null) {
            messageLabelID.setText("before trying to delete, please select valid customer by id");
        } else {
            // delete all appointment records using a foreign key
            AppointmentsDao appointmentsDao = new AppointmentsDao();
            List<Appointments> appointments = appointmentsDao
                    .findAll()
                    .stream()
                    .filter(a -> a.getCustomers().equals(currCustomer))
                    .collect(Collectors.toList());
            appointments.forEach(appointmentsDao::delete);

            String name = currCustomer.getName(), id = currCustomer.getId() + "";
            customerDao.delete(currCustomer);
            clearButtonAction(event);
            messageLabelID.setText(name + " (" + id + ") deleted successfully.");
            currCustomer = null;
        }
    }

    // collect data from filed and create new customer
    private void addCustomerButtonAction(ActionEvent event) {
        try {

            if (!customerIDTxtFldID.getText().isEmpty()) {
                messageLabelID.setText("Customer id filed should be empty," +
                        " system will automatically create the id");
            } else {

                String name = nameTxtFldID.getText(), phone = phoneTxtFldID.getText(),
                        address = addressTxtFldID.getText(), postcode = postcodeTxtFldID.getText();
                FirstLevelDivisions divisions = divisionsDao.findAll().stream()
                        .filter(d -> d.getDivisions().equals(divisionComboBxID.getValue()))
                        .collect(Collectors.toList()).get(0);

                if (name.isEmpty() || phone.isEmpty() || address.isEmpty() || postcode.isEmpty()) {
                    messageLabelID.setText("Please fill all information to create a customer!");
                } else {
                    // create customer
                    Customers customers = new Customers();
                    customers.setName(name);
                    customers.setPhone(phone);
                    customers.setAddress(address);
                    customers.setPostalCode(postcode);
                    customers.setDivisionID(divisions);
                    customers.setCreatedDate(LocalDateTime.now());
                    customers.setLasUpdate(LocalDateTime.now());
                    customers.setCreatedBy(users.getUserName());
                    customers.setLastUpdateBy(users.getUserName());

                    customers = customerDao.createOrUpdate(customers);
                    currCustomer = customers;
                    customerIDTxtFldID.setText(customers.getId() + "");

                    messageLabelID.setText("customer created successfully.");
                }
            }
        } catch (Exception e) {
            messageLabelID.setText("something error while creating a customer.");
            e.printStackTrace();
        }
    }


    // clear all fields and current customer
    private void clearButtonAction(ActionEvent event) {
        currCustomer = null;
        customerIDTxtFldID.clear();
        nameTxtFldID.clear();
        phoneTxtFldID.clear();
        addressTxtFldID.clear();
        postcodeTxtFldID.clear();
        messageLabelID.setText("");
    }

    // handle find button action
    private void findCustomerButtonAction(ActionEvent event) {
        try {
            // get customer
            int customerID = Integer.parseInt(customerIDTxtFldID.getText());
            currCustomer = customerDao.findOne(customerID);

            if (currCustomer != null) {
                // update data in fields
                nameTxtFldID.setText(currCustomer.getName());
                addressTxtFldID.setText(currCustomer.getAddress());
                phoneTxtFldID.setText(currCustomer.getPhone());
                postcodeTxtFldID.setText(currCustomer.getPostalCode());

                // set country and division,
                countryComboBxID.setValue(countriesDao.findOne(
                        currCustomer.getDivisionID().getCountryID()).getCountry()
                );
                divisionComboBxID.setValue(currCustomer.getDivisionID().getDivisions());
                messageLabelID.setText("");
            } else {
                clearButtonAction(event);
                messageLabelID.setText("Customer not found, please try again with valid customer id.");
            }

        } catch (Exception e) {
            messageLabelID.setText("Customer id should be a number!, please try again.");
        }
    }

    // Create a combo box - country box
    private void updateCountryComboBox() {
        List<String> countries = countriesDao.findAll()
                .stream()
                .map(Countries::getCountry)
                .collect(Collectors.toList());
        countryComboBxID.setItems(FXCollections.observableArrayList(countries));
        countryComboBxID.setValue(countries.get(0));
        countryComboBxID.setOnAction(e -> {
            currentCountry.set(countriesDao.findAll()
                    .stream()
                    .filter(c -> c.getCountry().equals(countryComboBxID.getValue()))
                    .collect(Collectors.toList()).get(0));
            updateDivisionComboBox();
        });
    }

    // Create a combo box - divisions box
    private void updateDivisionComboBox() {
        List<String> divisions = divisionsDao.findAll()
                .stream()
                .filter(f -> f.getCountryID() == currentCountry.get().getId())
                .map(FirstLevelDivisions::getDivisions)
                .collect(Collectors.toList());
        divisionComboBxID.setItems(FXCollections.observableArrayList(divisions));
        divisionComboBxID.setValue(divisions.get(0));
    }
}
