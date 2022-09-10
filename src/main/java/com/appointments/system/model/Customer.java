package com.appointments.system.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
public class Customer implements Serializable {

    //    `Customer_ID`     int       NOT NULL AUTO_INCREMENT,
    //    `Customer_Name`   varchar(50)    DEFAULT NULL,
    //    `Address`         varchar(100)   DEFAULT NULL,
    //    `Postal_Code`     varchar(50)    DEFAULT NULL,
    //    `Phone`           varchar(50)    DEFAULT NULL,
    //    `Create_Date`     datetime       DEFAULT NULL,
    //    `Created_By`      varchar(50)    DEFAULT NULL,
    //    `Last_Update`     timestamp NULL DEFAULT NULL,
    //    `Last_Updated_By` varchar(50)    DEFAULT NULL,
    //    `Division_ID`     int       NOT NULL,

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

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", phone='" + phone + '\'' +
                ", createdDate=" + createdDate +
                ", createdBy='" + createdBy + '\'' +
                ", lasUpdate=" + lasUpdate +
                ", lastUpdateBy='" + lastUpdateBy + '\'' +
                '}';
    }
}
