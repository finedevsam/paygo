package com.paygo.paygo.dto;

import lombok.Data;

@Data
public class FundTransferDto {
    private String debitAccount;
    private String creditAccount;
    private String amount;
}
