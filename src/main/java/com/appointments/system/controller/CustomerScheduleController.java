package com.appointments.system.controller;

import com.appointments.system.model.Appointments;
import com.appointments.system.model.Contacts;
import com.appointments.system.model.Customers;
import com.appointments.system.repo.ContactsDao;
import com.appointments.system.repo.CustomerDao;
import com.appointments.system.utils.DataTraveler;
import com.appointments.system.utils.DataUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CustomerScheduleController implements Initializable, DataTraveler {

    public Label messageTxtLblID;
    public TableView<Appointments> customerTableViewID;
    public ComboBox<String> customerComBoxID;
    Object[] data;

    @Override
    public void data(Object... o) {
        this.data = o;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateContactComboBox();
        initTableView();
        setupTableView();

        customerComBoxID.setOnAction(e -> {
            setupTableView();
        });
    }

    // update data based on selected contact
    private synchronized void setupTableView() {
        ObservableList<Appointments> appointmentsByContacts = FXCollections.observableArrayList(
                DataUtil.getAppointmentsByDays(30).
                        stream()
                        .filter(a -> a.getCustomers().getName().equals(customerComBoxID.getValue()))
                        .collect(Collectors.toList())
        );

        customerTableViewID.setItems(appointmentsByContacts);
        customerTableViewID.refresh();
    }

    // update & init combobox
    private synchronized void updateContactComboBox() {
        List<String> contacts = new CustomerDao().findAll()
                .stream()
                .map(Customers::getName)
                .collect(Collectors.toList());
        customerComBoxID.setItems(FXCollections.observableArrayList(contacts));
        customerComBoxID.setValue(contacts.get(0));
    }

    /**
     * Init table view
     */
    private synchronized void initTableView() {
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
        customerTableViewID.getColumns().add(column1);
        customerTableViewID.getColumns().add(column2);
        customerTableViewID.getColumns().add(column3);
        customerTableViewID.getColumns().add(column4);
        customerTableViewID.getColumns().add(column6);
        customerTableViewID.getColumns().add(column7);
        customerTableViewID.getColumns().add(column8);
        customerTableViewID.getColumns().add(column9);
    }
}
