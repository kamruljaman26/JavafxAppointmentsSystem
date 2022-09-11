package com.appointments.system.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "countries")
public class Countries implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Country_ID")
    private int id;

    @Column(name = "Country")
    private String country;

    @Column(name = "Create_Date")
    private LocalDateTime createdDate;

    @Column(name = "Created_By")
    private String createdBy;

    @Column(name = "Last_Update")
    private LocalDateTime lasUpdate;

    @Column(name = "Last_Updated_By")
    private String lastUpdateBy;

    public Countries() {
    }

    public Countries(int id, String country, LocalDateTime createdDate, String createdBy,
                     LocalDateTime lasUpdate, String lastUpdateBy) {
        this.id = id;
        this.country = country;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.lasUpdate = lasUpdate;
        this.lastUpdateBy = lastUpdateBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    @Override
    public String toString() {
        return "Countries{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", createdDate=" + createdDate +
                ", createdBy='" + createdBy + '\'' +
                ", lasUpdate=" + lasUpdate +
                ", lastUpdateBy='" + lastUpdateBy + '\'' +
                '}';
    }
}
