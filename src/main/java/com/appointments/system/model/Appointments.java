package com.appointments.system.model;

import com.appointments.system.utils.DateTimeUtil;
import com.appointments.system.utils.LanguageUtil;

import javax.persistence.*;
import java.io.Serializable;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Table(name = "appointments")
public class Appointments implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Appointment_ID")
    private int id;

    @Column(name = "Title")
    private String title;

    @Column(name = "Description")
    private String description;

    @Column(name = "Location")
    private String location;

    @Column(name = "Type")
    private String type;

    @Column(name = "Start")
    private LocalDateTime start;

    @Column(name = "End")
    private LocalDateTime end;

    @Column(name = "Create_Date")
    private LocalDateTime createDate;

    @Column(name = "Created_By")
    private String createdBy;

    @Column(name = "Last_Update")
    private LocalDateTime lastUpdate;

    @Column(name = "Last_Updated_By")
    private String lastUpdatedBy;

    @OneToOne(targetEntity=Customers.class)
    @JoinColumn(name = "Customer_ID")
    private Customers customers;

    @OneToOne(targetEntity=Users.class)
    @JoinColumn(name = "User_ID")
    private Users users;

    @OneToOne(targetEntity=Contacts.class)
    @JoinColumn(name = "Contact_ID")
    private Contacts contacts;

    @Transient
    private LocalDateTime startLocal;
    @Transient
    private LocalDateTime endLocal;

    @Transient
    private ZonedDateTime d;

    public Appointments() {
    }

    public Appointments(int id, String title, String description, String location, String type, LocalDateTime start,
                        LocalDateTime end, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate,
                        String lastUpdatedBy, Customers customers, Users users, Contacts contacts) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.customers = customers;
        this.users = users;
        this.contacts = contacts;
    }

    // provide local time and date
    public LocalDateTime getStartLocal() {
        if(startLocal == null) {
            try {
                startLocal = LanguageUtil.changeDateTime(start.toString());
                return startLocal;
            } catch (ParseException e) {
                return startLocal;
            }
        }
        return startLocal;
    }

    // provide local time and date
    public LocalDateTime getEndLocal() {
        if(endLocal == null) {
            try {
                endLocal = LanguageUtil.changeDateTime(end.toString());
                return endLocal;
            } catch (ParseException e) {
                return endLocal;
            }
        }
        return endLocal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ZonedDateTime getStartUTC() {
        return start.atZone(DateTimeUtil.UTC_ZONE_ID);
    }
    public ZonedDateTime getStartSystem() {
        return start.atZone(DateTimeUtil.UTC_ZONE_ID).withZoneSameInstant(DateTimeUtil.SYSTEM_ZONE_ID);
    }

    public void setStart(ZonedDateTime start) {
        this.start = start.toLocalDateTime();
    }

    public ZonedDateTime getEndUTC() {
        return end.atZone(DateTimeUtil.UTC_ZONE_ID);
    }
    public ZonedDateTime getEndSystem() {
        return end.atZone(DateTimeUtil.UTC_ZONE_ID).withZoneSameInstant(DateTimeUtil.SYSTEM_ZONE_ID);
    }

    public void setEnd(ZonedDateTime end) {
        this.end = end.toLocalDateTime();
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
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

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Contacts getContacts() {
        return contacts;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "Appointments{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", type='" + type + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", createDate=" + createDate +
                ", createdBy='" + createdBy + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                ", customers=" + customers +
                ", users=" + users +
                ", contacts=" + contacts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointments that = (Appointments) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
