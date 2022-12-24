package com.appointments.system.controller;

import com.appointments.system.model.Appointments;
import com.appointments.system.utils.DataTraveler;
import com.appointments.system.utils.DataUtil;
import com.appointments.system.utils.FXUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class AppointmentsHomeController implements Initializable, DataTraveler {

    @FXML
    public Label appIn15MinTxtLblID;
    @FXML
    public Button customerReportBtnID;
    @FXML
    private Label weekLblID;
    @FXML
    private Label monthLblID;
    @FXML
    private Button contactReportBtnID;
    @FXML
    private TableView<Appointments> in15minTableViewID;

    Object[] data;
    private AnchorPane anchorPane;

    @Override
    public void data(Object... o) {
        this.data = o;
        anchorPane = (AnchorPane) o[1];
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<Appointments> appointmentsBy1Day = DataUtil.getAppointmentsByDays(1);
        ObservableList<Appointments> appointmentsBy7Days = DataUtil.getAppointmentsByDays(7);
        ObservableList<Appointments> appointmentsBy30Days = DataUtil.getAppointmentsByDays(30);

        // total week
        weekLblID.setText(appointmentsBy7Days.size() + "");
        monthLblID.setText(appointmentsBy30Days.size() + "");

        // show appoints in 15 min
        ObservableList<Appointments> appointmentsBy15Min = FXCollections.observableArrayList();

        // todo: verify and fixed the issue
        // algorithm:: find if any appointments available in 15 min
        appointmentsBy1Day.forEach(a -> {
            LocalTime localTime = LocalTime.now();
            LocalTime localTime15 = localTime.plusMinutes(15);

            LocalTime aTime = a.getStartSystem().toLocalTime();

            if (aTime.compareTo(localTime) >= 0 && aTime.compareTo(localTime15) <= 0) {
                System.out.println(a);
                appointmentsBy15Min.add(a);
            }
        });

        if (appointmentsBy15Min.isEmpty()) {
            appIn15MinTxtLblID.setText("no appointments found in next 15 min");
        } else {
            appIn15MinTxtLblID.setText(appointmentsBy15Min.size() + " appointments found in next 15 min");
            init15MinTableView(appointmentsBy15Min);
        }

        // contact
        contactReportBtnID.setOnAction(this::contactReportButtonAction);
        customerReportBtnID.setOnAction(this::customerReportButtonAction);
    }

    // contact report button action
    private void contactReportButtonAction(ActionEvent event) {
        FXUtil.loadAnchorView(getClass(), FXUtil.CONTACT_REPORT, anchorPane); // replace view
    }

    // contact report button action
    private void customerReportButtonAction(ActionEvent event) {
        FXUtil.loadAnchorView(getClass(), FXUtil.CUSTOMER_REPORT, anchorPane); // replace view
    }

    /**
     * Init table view
     *
     * @param appointmentsBy15Min
     */
    private void init15MinTableView(ObservableList<Appointments> appointmentsBy15Min) {

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
        column7.setCellValueFactory(new PropertyValueFactory<>("startLocal"));

        TableColumn<Appointments, String> column8 = new TableColumn<>("End");
        column8.setCellValueFactory(new PropertyValueFactory<>("endLocal"));

        TableColumn<Appointments, String> column9 = new TableColumn<>("Customer");
        column9.setCellValueFactory(new PropertyValueFactory<>("customers"));

        TableColumn<Appointments, String> column10 = new TableColumn<>("User");
        column10.setCellValueFactory(new PropertyValueFactory<>("users"));

        // add columns
        in15minTableViewID.getColumns().add(column1);
        in15minTableViewID.getColumns().add(column2);
        in15minTableViewID.getColumns().add(column3);
        in15minTableViewID.getColumns().add(column4);
        in15minTableViewID.getColumns().add(column5);
        in15minTableViewID.getColumns().add(column6);
        in15minTableViewID.getColumns().add(column7);
        in15minTableViewID.getColumns().add(column8);
        in15minTableViewID.getColumns().add(column9);
        in15minTableViewID.getColumns().add(column10);

        in15minTableViewID.setItems(appointmentsBy15Min);
        in15minTableViewID.refresh();

    }
}
