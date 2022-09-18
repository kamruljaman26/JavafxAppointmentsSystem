package com.appointments.system.model;

import com.appointments.system.utils.LanguageUtil;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
public class Customers implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Customer_ID")
    private int id;

    @Column(name = "Customer_Name")
    private String name;

    @Column(name = "Address")
    private String address;

    @Column(name = "Postal_Code")
    private String postalCode;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Create_Date")
    private LocalDateTime createdDate;

    @Column(name = "Created_By")
    private String createdBy;

    @Column(name = "Last_Update")
    private LocalDateTime lasUpdate;

    @Column(name = "Last_Updated_By")
    private String lastUpdateBy;

    @OneToOne(targetEntity = FirstLevelDivisions.class)
    @JoinColumn(name = "Division_ID")
    private FirstLevelDivisions divisionID;

    public Customers() {
    }

    public Customers(int id, String name, String address, String postalCode, String phone, LocalDateTime createdDate, String createdBy,
                     LocalDateTime lasUpdate, String lastUpdateBy, FirstLevelDivisions divisionID) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.lasUpdate = lasUpdate;
        this.lastUpdateBy = lastUpdateBy;
        this.divisionID = divisionID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getLasUpdate() {
        return lasUpdate;
    }

    public void setLasUpdate(LocalDateTime lasUpdate) {
        this.lasUpdate = lasUpdate;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public FirstLevelDivisions getDivisionID() {
        return divisionID;
    }

    public void setDivisionID(FirstLevelDivisions divisionID) {
        this.divisionID = divisionID;
    }

    @Override
    public String toString() {
        return id + "";
    }
}
