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
import java.time.*;
import java.time.format.DateTimeParseException;
import java.util.Date;
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
        startDatePickerID.setValue(LocalDate.now());

        // init views
        updateContactsComboBox();

        // init button
        searchBtnID.setOnAction(this::findAppointmentsButtonAction);
        clearFldsBtnID.setOnAction(this::clearButtonAction);
        addBtnID.setOnAction(this::addAppointmentsButtonAction);
        deleteBtnID.setOnAction(this::deleteAppointmentsButtonAction);
    }


    // delete button action
    public void deleteAppointmentsButtonAction(ActionEvent event) {
        if (curAppointment == null) {
            messageLabelID.setText("before trying to delete, please select valid appointment by id");
        } else {
            // delete all appointment records using a foreign key
            AppointmentsDao appointmentsDao = new AppointmentsDao();
            appointmentsDao.delete(curAppointment);
            String name = curAppointment.getCustomers().getName(), contactName = curAppointment.getContacts().getContactName();

            clearButtonAction(event);
            messageLabelID.setText(
                    name + "'s appointments with " +
                            contactName + " is deleted successfully."
            );
        }
    }

    // handle update button action
    private void addAppointmentsButtonAction(ActionEvent event) {
        try {
            String title = titleTxtFldID.getText(), description = descriptionTxtFldID.getText(),
                    location = locationTxtFldID.getText(), type = typeTxtFldID.getText(),
                    customerId = customerIDTxtFldID.getText();

            // if search is not empty
            if (!searchTxtFldID.getText().isEmpty()) {
                messageLabelID.setText("Appointments id filed should be empty," +
                        " system will automatically create the id");
            }
            // if any fields are empty
            else if (title.isEmpty() ||
                    description.isEmpty() ||
                    location.isEmpty() ||
                    customerId.isEmpty() ||
                    type.isEmpty() ||
                    startTimeTxtFldID.getText().isEmpty() ||
                    endTimeTxtFldID.getText().isEmpty()
            ) {
                messageLabelID.setText("Please fill all information to create a appointments.");
            } else {

                Customers customers = customerDao.findOne(Integer.parseInt(customerId));

                // convert selected time to UTC time
                LocalDateTime startDateTime = LocalDateTime.of(startDatePickerID.getValue(),
                        LocalTime.parse(startTimeTxtFldID.getText()));
                LocalDateTime endDateTime = LocalDateTime.of(startDatePickerID.getValue(),
                        LocalTime.parse(endTimeTxtFldID.getText()));
                startDateTime.atZone(ZoneOffset.UTC);
                endDateTime.atZone(ZoneOffset.UTC);

                // check time overlapping

                // meeting end before it start
                if (startDateTime.toLocalTime().compareTo(endDateTime.toLocalTime()) >= 0) {
                    messageLabelID.setText("end time can be same or smaller from start time.");
                }

                // should  between business hours ( 8:00 a.m. to 10:00 p.m)
                else if (startDateTime.toLocalTime().compareTo(LocalTime.parse("08:00")) == 0 ||
                        startDateTime.toLocalTime().compareTo(LocalTime.parse("22:00")) == 0 ||
                        endDateTime.toLocalTime().compareTo(LocalTime.parse("08:00")) == 0 ||
                        endDateTime.toLocalTime().compareTo(LocalTime.parse("22:00")) == 0
                ) {
                    messageLabelID.setText("appointment should  between business hours ( 8:00 a.m. to 10:00 p.m)");
                }

                // invalid customer
                else if (customers == null) {
                    messageLabelID.setText("Customer not found, invalid customer id to create an appointments.");
                }

                // create customer
                else {
                    Appointments appointments = new Appointments();
                    appointments.setTitle(title);
                    appointments.setDescription(description);
                    appointments.setLocation(location);
                    appointments.setType(type);
                    appointments.setContacts(curContact.get());
                    appointments.setCustomers(customers);

                    appointments.setUsers(users);
                    appointments.setCreateDate(LocalDateTime.now());
                    appointments.setCreatedBy(users.getUserName());
                    appointments.setLastUpdate(LocalDateTime.now());
                    appointments.setLastUpdatedBy(users.getUserName());

                    appointments.setStart(startDateTime);
                    appointments.setEnd(endDateTime);

                    curAppointment = appointmentsDao.createOrUpdate(appointments);

                    searchTxtFldID.setText(appointments.getId() + "");
                    searchTxtFldID.setDisable(true);
                    messageLabelID.setText("customer created successfully.");
                }
            }
        } catch (DateTimeParseException e) {
            messageLabelID.setText("start and end time should be in a formatted way, format (HH:MM or HH:MM:ss)");
        }
    }

    // handle find button action
    private void findAppointmentsButtonAction(ActionEvent event) {
        try {
            // get appoints id
            int appointmentID = Integer.parseInt(searchTxtFldID.getText().trim());
            curAppointment = appointmentsDao.findOne(appointmentID);

            if (curAppointment != null) {
                searchTxtFldID.setDisable(true);
                messageLabelID.setText("");
                titleTxtFldID.setText(curAppointment.getTitle());
                descriptionTxtFldID.setText(curAppointment.getDescription());
                locationTxtFldID.setText(curAppointment.getLocation());
                typeTxtFldID.setText(curAppointment.getType());
                contactComboBxID.setValue(curAppointment.getContacts().getContactName());
                customerIDTxtFldID.setText(curAppointment.getCustomers().getId() + "");

                // process datetime to date and time
                LocalDateTime start = curAppointment.getStart();
                LocalDateTime end = curAppointment.getEnd();
                startDatePickerID.setValue(start.toLocalDate());
                startTimeTxtFldID.setText(start.toLocalTime().toString());
                endTimeTxtFldID.setText(end.toLocalTime().toString());
            } else {
                clearButtonAction(event);
                messageLabelID.setText("Appointment not found, please try again with a valid appointment id.");
            }

        } catch (Exception e) {
            messageLabelID.setText("Appointment id should be a number!, please try again with number input.");
            e.printStackTrace();
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
        endTimeTxtFldID.clear();
        messageLabelID.setText("");
        searchTxtFldID.setDisable(false);
    }

    // update contact combo box
    private void updateContactsComboBox() {
        List<String> contacts = contactsDao.findAll()
                .stream()
                .map(Contacts::getContactName)
                .collect(Collectors.toList());
        contactComboBxID.setItems(FXCollections.observableList(contacts));
        contactComboBxID.setValue(contacts.get(0));
        curContact.set(contactsDao.findAll().get(0));

        contactComboBxID.setOnAction(e -> {
            curContact.set(contactsDao.findAll()
                    .stream()
                    .filter(c -> c.getContactName().equals(contactComboBxID.getValue()))
                    .collect(Collectors.toList()).get(0));
            System.out.println(curContact.get().toString());
        });
    }
}
