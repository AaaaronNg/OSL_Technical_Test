package org.example;

import java.util.ArrayList;
import java.util.List;

public class TransactionBookDAOService {
    private  static List<TransactionBook> transactionBooks = new ArrayList<>();


    public TransactionBook findOne(String clientId){
        return transactionBooks.stream().filter(transactionBook -> transactionBook.getClientId().equals(clientId))
                .findFirst().orElse(null);
    }

    public List<TransactionBook> findAll(){
        return transactionBooks;
    }

    public void save(TransactionBook transactionBook){
        transactionBooks.add(transactionBook);
    }



}
