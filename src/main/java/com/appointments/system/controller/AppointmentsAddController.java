package com.appointments.system.controller;

import com.appointments.system.model.*;
import com.appointments.system.repo.*;
import com.appointments.system.utils.DataTraveler;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import net.bytebuddy.asm.Advice;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;


public class AppointmentsAddController implements Initializable, DataTraveler {

    @javafx.fxml.FXML
    private DatePicker startDatePickerID;
    @javafx.fxml.FXML
    private TextField typeTxtFldID;
    @javafx.fxml.FXML
    private Button deleteBtnID;
    @javafx.fxml.FXML
    private TextField endTimeTxtFldID;
    @javafx.fxml.FXML
    private TextArea descriptionTxtFldID;
    @javafx.fxml.FXML
    private Button addBtnID;
    @javafx.fxml.FXML
    private TextField searchTxtFldID;
    @javafx.fxml.FXML
    private Button searchBtnID;
    @javafx.fxml.FXML
    private TextField customerIDTxtFldID;
    @javafx.fxml.FXML
    private DatePicker endDatepickerID;
    @javafx.fxml.FXML
    private Button clearFldsBtnID;
    @javafx.fxml.FXML
    private TextField locationTxtFldID;
    @javafx.fxml.FXML
    private TextField startTimeTxtFldID;
    @javafx.fxml.FXML
    private ComboBox contactComboBxID;
    @javafx.fxml.FXML
    private Button updateBtnID;
    @javafx.fxml.FXML
    private Label messageLabelID;
    @javafx.fxml.FXML
    private TextField titleTxtFldID;

    private AppointmentsDao appointmentsDao;
    private CustomerDao customerDao;
    private ContractsDao contactsDao;

    private AtomicReference<Contacts> curContact;
    private Appointments curAppointment;
    private Users users;

    @Override
    public void data(Object... o) {
        users = (Users) o[0];
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // init variables
        appointmentsDao = new AppointmentsDao();
        customerDao = new CustomerDao();
        contactsDao = new ContractsDao();
        curContact = new AtomicReference<>();

        // init
        updateContactsComboBox();

        // init button
        searchBtnID.setOnAction(this::findAppointmentsButtonAction);
        clearFldsBtnID.setOnAction(this::clearButtonAction);
    }

    // handle find button action
    private void findAppointmentsButtonAction(ActionEvent event) {
        try {
            // get appoints id
            int appointmentID = Integer.parseInt(searchTxtFldID.getText());
            curAppointment = appointmentsDao.findOne(appointmentID);

            if (curAppointment != null) {
                titleTxtFldID.setText(curAppointment.getTitle());
                descriptionTxtFldID.setText(curAppointment.getDescription());
                locationTxtFldID.setText(curAppointment.getLocation());
                typeTxtFldID.setText(curAppointment.getType());
                contactComboBxID.setValue(curAppointment.getContacts().getContactName());
                customerIDTxtFldID.setText(curAppointment.getCustomers().getId()+"");

                // process datetime to date and time
                LocalDateTime start = curAppointment.getStart();
                LocalDateTime end = curAppointment.getEnd();
                startDatePickerID.setValue(start.toLocalDate());
                startTimeTxtFldID.setText(start.toLocalTime().toString());
                endDatepickerID.setValue(end.toLocalDate());
                endTimeTxtFldID.setText(end.toLocalTime().toString());
                messageLabelID.setText("");

            } else {
                clearButtonAction(event);
                messageLabelID.setText("Appointment not found, please try again with a valid appointment id.");
            }

        } catch (Exception e) {
            messageLabelID.setText("Appointment id should be a number!, please try again with number input.");
        }
    }

    // clear button action
    private void clearButtonAction(ActionEvent event) {
        // clear properties
        curAppointment = null;

        // clear fields
        titleTxtFldID.clear();
        searchTxtFldID.clear();
        typeTxtFldID.clear();
        descriptionTxtFldID.clear();
        locationTxtFldID.clear();
        customerIDTxtFldID.clear();
        startDatePickerID.setValue(LocalDate.now());
        startTimeTxtFldID.clear();
        endDatepickerID.setValue(LocalDate.now());
        endTimeTxtFldID.clear();
        messageLabelID.setText("");
    }

    // update contact combo box
    private void updateContactsComboBox() {
        List<String> contacts = contactsDao.findAll()
                .stream()
                .map(Contacts::getContactName)
                .collect(Collectors.toList());
        contactComboBxID.setItems(FXCollections.observableList(contacts));

        contactComboBxID.setOnAction(e -> {
            curContact.set(contactsDao.findAll()
                    .stream()
                    .filter(c -> c.getContactName().equals(contactComboBxID.getValue()))
                    .collect(Collectors.toList()).get(0));
            System.out.println(curContact.get().toString());
        });
    }
}
