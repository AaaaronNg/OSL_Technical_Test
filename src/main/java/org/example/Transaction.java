package org.example;

import java.util.Currency;
import java.util.Date;

public class Transaction {
    private Integer transactionId;
    private String date;
    private String currency;
    private String operation;
    private Double amount;

    public Transaction() {
    }

    public Transaction(String date, String currency, String operation, Double amount) {
        this.date = date;
        this.currency = currency;
        this.operation = operation;
        this.amount = amount;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getTransactionId() {
        return transactionId;
    }



    public void setDate(String date) {
        this.date = date;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }


    public String getDate() {
        return date;
    }

    public String getCurrency() {
        return currency;
    }

    public String getOperation() {
        return operation;
    }

    public Double getAmount() {
        return amount;
    }
}
