package com.appointments.system.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "contacts")
public class Contacts implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Contact_ID")
    private int id;

    @Column(name = "Contact_Name")
    private String contactName;

    @Column(name = "Email")
    private String email;

    public Contacts() {
    }

    public Contacts(int id, String contactName, String email) {
        this.id = id;
        this.contactName = contactName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "id=" + id +
                ", contactName='" + contactName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
