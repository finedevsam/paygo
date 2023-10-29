package com.paygo.paygo.utils;

import com.paygo.paygo.entity.TransactionHistory;
import com.paygo.paygo.repository.TransactionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Transactions {
    /**
     * This is a Javadoc comment for the MyClass class.
     * You can provide a detailed description of the class here.
     */

    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;

    public void history(
            String accountNumber,
            String transactionCode,
            String amount,
            String balance,
            String transactionType,
            String transactionReferenceNumber,
            String counterPartyAccountNumber,
            String counterPartyBankCode,
            String counterPartyBankName,
            String currencyCode,
            String exchangeRate,
            String narration,
            String branchCode,
            String terminalId,
            String userId,
            String channel,
            String checkNumber,
            String referenceNumber,
            String paymentType,
            String processingStatus,
            String errorCode,
            String approvalStatus,
            String approvedBy,
            String comments
    ){
        //  Instantiate the Transaction History Class
        TransactionHistory history = new TransactionHistory();

        // Set the data
        history.setAccountNumber(accountNumber);
        history.setTransactionCode(transactionCode);
        history.setAmount(amount);
        history.setBalance(balance);
        history.setTransactionType(transactionType);
        history.setTransactionReferenceNumber(transactionReferenceNumber);
        history.setCounterPartyAccountNumber(counterPartyAccountNumber);
        history.setCounterPartyBankCode(counterPartyBankCode);
        history.setCounterPartyBankName(counterPartyBankName);
        history.setCurrencyCode(currencyCode);
        history.setExchangeRate(exchangeRate);
        history.setNarration(narration);
        history.setBranchCode(branchCode);
        history.setTerminalId(terminalId);
        history.setUserId(userId);
        history.setChannel(channel);
        history.setCheckNumber(checkNumber);
        history.setReferenceNumber(referenceNumber);
        history.setPaymentType(paymentType);
        history.setProcessingStatus(processingStatus);
        history.setErrorCode(errorCode);
        history.setApprovalStatus(approvalStatus);
        history.setApprovedBy(approvedBy);
        history.setComments(comments);

        // Save the records instance to DB
        transactionHistoryRepository.save(history);

    }
}
