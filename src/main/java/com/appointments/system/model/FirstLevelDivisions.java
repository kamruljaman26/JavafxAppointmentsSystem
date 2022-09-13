package com.appointments.system.model;

import com.appointments.system.repo.CountriesDao;
import com.appointments.system.repo.CustomerDao;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "first_level_divisions")
public class FirstLevelDivisions implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Division_ID")
    private int id;

    @Column(name = "Division")
    private String divisions;

    @Column(name = "Create_Date")
    private LocalDateTime createdDate;

    @Column(name = "Created_By")
    private String createdBy;

    @Column(name = "Last_Update")
    private LocalDateTime lastUpdate;

    @Column(name = "Last_Updated_By")
    private String lastUpdatedBy;

    @Column(name = "Country_ID")
    private int countryID;

    public FirstLevelDivisions() {
    }

    public FirstLevelDivisions(int id, String divisions, LocalDateTime createdDate, String createdBy,
                               LocalDateTime lastUpdate, String lastUpdatedBy, int countryID) {
        this.id = id;
        this.divisions = divisions;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.countryID = countryID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDivisions() {
        return divisions;
    }

    public void setDivisions(String divisions) {
        this.divisions = divisions;
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

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    @Override
    public String toString() {
        Countries countries = new CountriesDao().findOne(getCountryID());
        return getDivisions()+", "+countries.getCountry();
    }
}
