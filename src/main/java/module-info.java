module JavaFXAppointmentsSystem {

//    javafx
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

//    mysql and database
    requires mysql.connector.java;
    requires com.google.common;
    requires java.persistence;
    requires java.naming;

//    hibernate
    requires org.hibernate.commons.annotations;
    requires org.hibernate.validator;
    requires org.hibernate.orm.core;

//    custom packages and
    opens com.appointments.system.controller to javafx.fxml;
    opens com.appointments.system.repo;
    opens com.appointments.system.model;
    opens com.appointments.system.utils;

    exports com.appointments.system.controller;
}