package com.paygo.paygo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tbl_customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true)
    private String cif;

    @Column(nullable = false, unique = true)
    private String mnemonic;

    @Column(nullable = false, name = "first_name")
    private String firstName;

    @Column(nullable = false, name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(nullable = false, name = "maiden_name")
    private String maidenName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, name = "id_type")
    private String idType;

    @Column(nullable = false, name = "id_no", unique = true)
    private String idNo;

    @Column(nullable = false, name = "mobile_no", unique = true)
    private String mobileNo;

    @Column(name = "alt_mobile_no")
    private String altMobileNo;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;
}
