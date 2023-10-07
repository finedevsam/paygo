package com.paygo.paygo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tbl_account_officer")
public class AccountOfficer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String code;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "mobile_number")
    private String mobileNumber;

    @Column(nullable = false)
    private String email;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;
}
