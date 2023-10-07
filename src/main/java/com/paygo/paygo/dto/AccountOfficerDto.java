package com.paygo.paygo.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class AccountOfficerDto {
    private String name;
    private String mobileNumber;
    private String email;
}
