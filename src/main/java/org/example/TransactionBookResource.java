package org.example;

import java.util.List;

public class TransactionBookResource {
    private TransactionBookDAOService service;


    public TransactionBookResource() {
        service = new TransactionBookDAOService();
    }

    public List<TransactionBook> getTransactionBooks(){
        return service.findAll();
    }

    public TransactionBook getTransactionBook(String clientId){
        TransactionBook transactionBook = service.findOne(clientId);
        return transactionBook;
    }


    public void printStatement(String clientId, String userName){
        List<Transaction> transactions = getTransactionBook(clientId).getTransactions();

        System.out.println("Client Name: "+ userName);
        System.out.println(
                String.format("%-25s%-20s%-20s%s" ,"Date","Currency","Operation", "Amount" )
        );

        for(Transaction transaction:transactions){
            String symbol = "";
            if(transaction.getOperation().equals("Deposit")){
                symbol = "+";
            }
            if(transaction.getOperation().equals("Withdrawal")
                    ||transaction.getOperation().equals("Withdrawal FEE")
                    ||transaction.getOperation().equals("Transfer FEE")){
                symbol = "-";
            }
            System.out.println(
                    String.format("%-25s%-20s%-20s%s%.1f" ,transaction.getDate(),
                            transaction.getCurrency(),
                            transaction.getOperation(),
                            symbol,
                            transaction.getAmount() )
            );
        }
    }

    public void saveTransactionBook(TransactionBook transactionBook){
        service.save(transactionBook);
    }


    public void saveDefaultTransactionBook(AccountResource accountResource){

        TransactionBook transactionBook_1 = new TransactionBook(accountResource.getAccount(8080).getClientId());
        TransactionBook transactionBook_2 = new TransactionBook(accountResource.getAccount(9090).getClientId());
        TransactionBook transactionBook_3 = new TransactionBook(accountResource.getAccount(1010).getClientId());
        service.save(transactionBook_1);
        service.save(transactionBook_2);
        service.save(transactionBook_3);
    }
}
