package com.paygo.paygo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "tbl_account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonIgnore
    private String id;

    @Column(nullable = false, unique = true)
    private String mnemonic;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "working_balance")
    private String workingBalance = "0";

    @Column(name = "closing_balance")
    private String closingBalance = "0";

    @Column(name = "opening_balance")
    private String openingBalance = "0";

    @Column(name = "account_type", nullable = false)
    private String accountType;

    @Column(name = "account_officer", nullable = false)
    private String accountOfficer;

    private boolean pnd = Boolean.FALSE;

    @Column(name = "is_active")
    private boolean isActive = Boolean.TRUE;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer customer;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;
}
