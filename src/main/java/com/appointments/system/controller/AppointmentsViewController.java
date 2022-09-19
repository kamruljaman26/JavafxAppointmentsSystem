package com.appointments.system.controller;

import com.appointments.system.model.Appointments;
import com.appointments.system.repo.AppointmentsDao;
import com.appointments.system.utils.DataTraveler;
import com.appointments.system.utils.LanguageUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class AppointmentsViewController implements Initializable, DataTraveler {


    public TableView<Appointments> weekTableViewID;
    public TableView<Appointments> monthTableViewID;

    // properties
    Object[] data;

    @Override
    public void data(Object... o) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadTableView(weekTableViewID, 7);
        loadTableView(monthTableViewID, 30);
    }

    // load month based appointments
    private static synchronized void loadTableView(TableView<Appointments> tableView, int days) {
        // create table columns
        TableColumn<Appointments, String> column1 = new TableColumn<>("ID");
        column1.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Appointments, String> column2 = new TableColumn<>("Title");
        column2.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Appointments, String> column3 = new TableColumn<>("Description");
        column3.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Appointments, String> column4 = new TableColumn<>("Location");
        column4.setCellValueFactory(new PropertyValueFactory<>("location"));

        TableColumn<Appointments, String> column5 = new TableColumn<>("Contact");
        column5.setCellValueFactory(new PropertyValueFactory<>("contacts"));

        TableColumn<Appointments, String> column6 = new TableColumn<>("Type");
        column6.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Appointments, String> column7 = new TableColumn<>("Start");
        column7.setCellValueFactory(new PropertyValueFactory<>("start"));

        TableColumn<Appointments, String> column8 = new TableColumn<>("End");
        column8.setCellValueFactory(new PropertyValueFactory<>("end"));

        TableColumn<Appointments, String> column9 = new TableColumn<>("Customer");
        column9.setCellValueFactory(new PropertyValueFactory<>("customers"));

        TableColumn<Appointments, String> column10 = new TableColumn<>("User");
        column10.setCellValueFactory(new PropertyValueFactory<>("users"));

        // add columns
        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);
        tableView.getColumns().add(column4);
        tableView.getColumns().add(column5);
        tableView.getColumns().add(column6);
        tableView.getColumns().add(column7);
        tableView.getColumns().add(column8);
        tableView.getColumns().add(column9);
        tableView.getColumns().add(column10);

        AppointmentsDao dao = new AppointmentsDao();
        ObservableList<Appointments> appointments = FXCollections.observableArrayList();
        Calendar calendar = Calendar.getInstance();

        for (int i = 0; i < days; i++) {
            Date time = calendar.getTime();

            // search all appointment to find out with date date range
            for (Appointments a : dao.findAll()) {
                int year1 = a.getLocalStart().getYear();
                int year2 = time.getYear() + 1900;

                int month1 = a.getLocalStart().toLocalDate().getMonthValue();
                int month2 = time.getMonth() + 1;

                int day1 = a.getLocalStart().toLocalDate().getDayOfMonth();
                int day2 = time.getDate();

                // if before current time
                if (year1 == year2 && month1 == month2 && day1 == day2) {

                    // filter today and already added appointments
                    boolean before = LocalTime.now().isAfter(a.getLocalStart().toLocalTime());
                    if (!appointments.contains(a) && !(i==0 && before)) {
                        a.setStart(a.getLocalStart());
                        a.setEnd(a.getLocalEnd());
                        appointments.add(a);
                    }
                }
            }

            // increase by 1 day
            calendar.add(Calendar.DATE, 1);
        }

        tableView.setItems(appointments);
        tableView.refresh();
    }

}
