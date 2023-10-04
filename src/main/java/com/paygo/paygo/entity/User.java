package com.paygo.paygo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true)
    private String email;

    @JsonIgnore
    private String password;

    @Column(name = "is_user")
    private Boolean isUser = false;

    @Column(name = "is_admin")
    private Boolean isAdmin = false;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;
}

