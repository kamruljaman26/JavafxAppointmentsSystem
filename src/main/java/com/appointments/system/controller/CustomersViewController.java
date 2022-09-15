package com.appointments.system.controller;

import com.appointments.system.model.Customers;
import com.appointments.system.repo.CustomerDao;
import com.appointments.system.utils.DataTraveler;
import com.appointments.system.utils.FXUtil;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CustomersViewController implements Initializable, DataTraveler {

    // fx ids
    public TableView<Customers> tableViewID;
    public Label titleLabelID;

    // properties
    Object[] data;

    @Override
    public void data(Object... o) {
        this.data = o;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // get all customers
        CustomerDao dao = new CustomerDao();
        List<Customers> all = dao.findAll();

        // create table columns
        TableColumn<Customers, String> column1 = new TableColumn<>("ID");
        column1.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Customers, String> column2 = new TableColumn<>("Name");
        column2.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Customers, String> column3 = new TableColumn<>("Address");
        column3.setCellValueFactory(new PropertyValueFactory<>("address"));

        TableColumn<Customers, String> column4 = new TableColumn<>("Postal Cone");
        column4.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        TableColumn<Customers, String> column5 = new TableColumn<>("Phone");
        column5.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableColumn<Customers, String> column6 = new TableColumn<>("Created Date");
        column6.setCellValueFactory(new PropertyValueFactory<>("createdDate"));

        TableColumn<Customers, String> column7 = new TableColumn<>("Created By");
        column7.setCellValueFactory(new PropertyValueFactory<>("createdBy"));

        TableColumn<Customers, String> column9 = new TableColumn<>("Las Update");
        column9.setCellValueFactory(new PropertyValueFactory<>("lasUpdate"));

        TableColumn<Customers, String> column10 = new TableColumn<>("Las Update By");
        column10.setCellValueFactory(new PropertyValueFactory<>("lastUpdateBy"));

        TableColumn<Customers, String> column11 = new TableColumn<>("First Level Division");
        column11.setCellValueFactory(new PropertyValueFactory<>("divisionID"));

        // add columns
        tableViewID.getColumns().add(column1);
        tableViewID.getColumns().add(column2);
        tableViewID.getColumns().add(column3);
        tableViewID.getColumns().add(column4);
        tableViewID.getColumns().add(column5);
        tableViewID.getColumns().add(column6);
        tableViewID.getColumns().add(column7);
        tableViewID.getColumns().add(column9);
        tableViewID.getColumns().add(column10);
        tableViewID.getColumns().add(column11);

        for (Customers customers:all) {
            tableViewID.getItems().add(customers);
        }
    }
}
