package com.paygo.paygo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_transaction_history")
public class TransactionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonIgnore
    private String id;

    @Column(name = "transaction_date", nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp transactionDate;

    @Column(name = "account_number", nullable = false, updatable = false)
    private String accountNumber;

    @Column(name = "posting_date", nullable = false, updatable = false)
    private Timestamp postingDate;

    @Column(name = "value_date", nullable = false, updatable = false)
    private Timestamp valueDate;

    @Column(name = "transaction_code", nullable = false)
    private String transactionCode;

    @Column(name = "amount", nullable = false)
    private String amount;

    @Column(name = "balance", nullable = false)
    private String balance;

    @Column(name = "transaction_type", nullable = false)
    private String transactionType;

    @Column(name = "transaction_reference_number", nullable = false)
    private String transactionReferenceNumber;

    @Column(name = "counter_party_account_number", nullable = false)
    private String counterPartyAccountNumber;

    @Column(name = "counter_party_bank_code", nullable = false)
    private String counterPartyBankCode;

    @Column(name = "counter_party_bank_name", nullable = false)
    private String counterPartyBankName;

    @Column(name = "currency_code", nullable = false)
    private String currencyCode;

    @Column(name = "exchange_rate")
    private String exchangeRate;

    @Column(name = "narration", nullable = false)
    private String narration;

    @Column(name = "branch_code")
    private String branchCode;

    @Column(name = "terminal_id")
    private String terminalId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "channel")
    private String channel;

    @Column(name = "check_number")
    private String checkNumber;

    @Column(name = "reference_number")
    private String referenceNumber;

    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "processing_status")
    private String processingStatus;

    @Column(name = "error_code")
    private String errorCode;

    @Column(name = "approval_status")
    private String approvalStatus;

    @Column(name = "approved_by")
    private String approvedBy;

    @Column(name = "comments")
    private String comments;
}
