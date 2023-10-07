package com.paygo.paygo.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CustomerDto {
    private String mnemonic;
    private String firstName;
    private String lastName;
    private String middleName;
    private String maidenName;
    private String address;
    private String country;
    private String state;
    private String email;
    private String idType;
    private String idNo;
    private String mobileNo;
    private String altMobileNo;
}
