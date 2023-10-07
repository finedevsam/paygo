package com.paygo.paygo.dto;

import lombok.Data;

@Data
public class AccountDto {
    private String mnemonic;
    private String accountType;
    private String accountOfficer;
    private String customerCif;
}
