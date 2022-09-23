package org.example;

import java.util.Scanner;

public class Bank {

    private AccountResource accountResource;
    private TransactionBookResource transactionBookResource;
    private Keypad keypad;
    private Timer timer;


    private static final int CREATEACCOUNT = 1;
    private static final int DEPOSIT = 2;
    private static final int WITHDRAWAL = 3;
    private static final int TRANSFER = 4;
    private static final int TRANSACTION_STATEMENT = 5;

    public Bank(){
        keypad = new Keypad();
        accountResource = new AccountResource();
        transactionBookResource = new TransactionBookResource();
        accountResource.saveDefaultAccount();
        transactionBookResource.saveDefaultTransactionBook(accountResource);
        timer = new Timer();
    }

    public void run(){
        while(true){
            int userChoice;
            userChoice = userMenu();
            switch (userChoice){
                case CREATEACCOUNT:
                    accountResource.creatAccount();
                    break;
                case DEPOSIT:
                    accountResource.deposit();
                    break;
                case WITHDRAWAL:
                    accountResource.withdrawal();
                    break;
                case TRANSFER:
                    accountResource.transfer();
                    break;
                case TRANSACTION_STATEMENT:
                    accountResource.printTransactionStatement();
                    break;
                default:
                    System.out.println("End");
            }
            // these codes are just for testing, showing all accounts' condition.
//            for (Account currentAccount:accountResource.getAllAccounts()){
//                System.out.print("ID: "+currentAccount.getAccountId());
//                System.out.print(" UserName: "+currentAccount.getUserName());
//                System.out.print(" Currency: "+currentAccount.getCurrency());
//                System.out.print(" Balance: "+currentAccount.getBalance());
//                System.out.print(" NumberOfWithdrawal: "+ currentAccount.getNumberOfWithdrawal());
//                System.out.println(" snapshotTime: "+ currentAccount.getSnapshotTime());
//                System.out.println(" ClientID: "+ currentAccount.getClientId());
//            }
        }
    }

    // user menu
    private int userMenu(){
        System.out.println("1. Create new account");
        System.out.println("2. Deposit money");
        System.out.println("3. Withdraw money");
        System.out.println("4. Transfer cash");
        System.out.println("5. Display transaction statement");
        int choice;

        do{
            System.out.println("Enter the choice");
            choice = keypad.getInteger();
        }while(choice<1 || choice>5);

        return choice;
    }

}
