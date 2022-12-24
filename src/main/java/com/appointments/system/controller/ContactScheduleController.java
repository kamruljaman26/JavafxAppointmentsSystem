package com.appointments.system.controller;

import com.appointments.system.model.Appointments;
import com.appointments.system.model.Contacts;
import com.appointments.system.model.FirstLevelDivisions;
import com.appointments.system.repo.ContactsDao;
import com.appointments.system.utils.DataTraveler;
import com.appointments.system.utils.DataUtil;
import com.appointments.system.utils.LanguageUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ContactScheduleController implements Initializable, DataTraveler {

    public TableView<Appointments> conactsTableViewID;
    public Label messageTxtLblID;
    public ComboBox<String> contactsComBoxID;
    Object[] data;

    @Override
    public void data(Object... o) {
        this.data = o;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateContactComboBox();
        init15MinTableView();
        setupTableView();

        contactsComBoxID.setOnAction(e -> {
            setupTableView();
        });
    }

    // update data based on selected contact
    private synchronized void setupTableView() {
        ObservableList<Appointments> appointmentsByContacts = FXCollections.observableArrayList(
                DataUtil.getAppointmentsByDays(30).
                        stream()
                        .filter(a -> a.getContacts().getContactName().equals(contactsComBoxID.getValue()))
                        .collect(Collectors.toList())
        );

        conactsTableViewID.setItems(appointmentsByContacts);
        conactsTableViewID.refresh();
    }

    // update & init combobox
    private synchronized void updateContactComboBox() {
        List<String> contacts = new ContactsDao().findAll()
                .stream()
                .map(Contacts::getContactName)
                .collect(Collectors.toList());
        contactsComBoxID.setItems(FXCollections.observableArrayList(contacts));
        contactsComBoxID.setValue(contacts.get(0));
    }

    /**
     * Init table view
     */
    private synchronized void init15MinTableView() {
        TableColumn<Appointments, String> column1 = new TableColumn<>("ID");
        column1.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Appointments, String> column2 = new TableColumn<>("Title");
        column2.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Appointments, String> column3 = new TableColumn<>("Description");
        column3.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Appointments, String> column4 = new TableColumn<>("Location");
        column4.setCellValueFactory(new PropertyValueFactory<>("location"));

        TableColumn<Appointments, String> column6 = new TableColumn<>("Type");
        column6.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Appointments, String> column7 = new TableColumn<>("Start");
        column7.setCellValueFactory(new PropertyValueFactory<>("startLocal"));

        TableColumn<Appointments, String> column8 = new TableColumn<>("Customer");
        column8.setCellValueFactory(param -> {
            StringProperty str = new SimpleStringProperty();
            str.setValue(param.getValue().getCustomers().getName());
            return str;
        });

        TableColumn<Appointments, String> column9 = new TableColumn<>("User");
        column9.setCellValueFactory(param -> {
            StringProperty str = new SimpleStringProperty();
            str.setValue(param.getValue().getUsers().getUserName());
            return str;
        });

        // add columns
        conactsTableViewID.getColumns().add(column1);
        conactsTableViewID.getColumns().add(column2);
        conactsTableViewID.getColumns().add(column3);
        conactsTableViewID.getColumns().add(column4);
        conactsTableViewID.getColumns().add(column6);
        conactsTableViewID.getColumns().add(column7);
        conactsTableViewID.getColumns().add(column8);
        conactsTableViewID.getColumns().add(column9);
    }
}
