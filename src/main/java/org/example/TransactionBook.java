package org.example;

import java.util.ArrayList;
import java.util.List;

public class TransactionBook {
    private String clientId;
    private List<Transaction> transactions;



    public TransactionBook(String clientId) {
        this.clientId = clientId;
        transactions = new ArrayList<>();
    }

    public TransactionBook() {
    }

    public String getClientId() {
        return clientId;
    }



    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
