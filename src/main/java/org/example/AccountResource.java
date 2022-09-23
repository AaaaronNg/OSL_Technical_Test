package org.example;

import java.util.Date;
import java.util.List;

public class AccountResource {

    private AccountDAOService accountDAOService;
    private Keypad keypad;
    private Timer timer;
    private TransactionBookResource transactionBookResource;
    private FormattedDate date;
    private static Integer id = 0;

    public AccountResource(){
        keypad = new Keypad();
        accountDAOService = new AccountDAOService();
        timer = new Timer();
        transactionBookResource = new TransactionBookResource();
        date = new FormattedDate();
    }

    public List<Account> getAllAccounts(){
        return accountDAOService.findAll();
    }




    public Account getAccount(Integer id){
        Account account = accountDAOService.findOne(id);
        return account;
    }
    public Account getAccountByClientId(String clientId){
        Account account = accountDAOService.findOneByClientId(clientId);
        return account;
    }

    public void listBalance(){
        Account account;
        Integer idInt;
        while (true){
            System.out.println("Please enter account ID to show the balance");
            String id = keypad.getString();
            while(!id.matches("[0-9]+")){
                System.out.println("Please enter a valid account ID");
                id = keypad.getString();
            }
            idInt = Integer.parseInt(id);
            account = getAccount(idInt);
            if(account != null){
                break;
            }
            System.out.println("Your account ID is incorrect");
        }

        if(account != null){
            account = getAccount(idInt);
            System.out.println("Account ID: "+idInt);
            System.out.println("The balance is "+account.getBalance());
        }
    }

    public void printTransactionStatement(){
        Account account;
        while (true){
            System.out.println("Please enter your ID card number");
            String clientId = keypad.getString();
            while(!clientId.matches("[a-zA-Z0-9]+")){
                System.out.println("Please enter a valid ID number");
                clientId = keypad.getString();
            }
            account = getAccountByClientId(clientId);
            if(account!=null){
                break;
            }
            System.out.println("Your ID card number is incorrect");
        }
        transactionBookResource.printStatement(account.getClientId(), account.getUserName());
    }

    public void deposit(){
        Account account;
        while (true){
            System.out.println("Please enter id account to deposit");
            String id = keypad.getString();
            while(!id.matches("[0-9]+")){
                System.out.println("Please enter a valid account ID");
                id = keypad.getString();
            }
            Integer idInt = Integer.parseInt(id);
            account = getAccount(idInt);
            if(account != null){
                break;
            }
            System.out.println("Your account ID is incorrect");
        }
        if(account != null){
            System.out.println("Please enter amount of money ("+account.getCurrency()+")");
            String quantity = keypad.getString();
            while(!quantity.matches("[0-9]+")){
                System.out.println("Please enter a valid amount of money");
                quantity = keypad.getString();
            }
            Double quantityDouble = Double.parseDouble(quantity);
            account.setBalance(account.getBalance()+quantityDouble);
            transactionBookResource.getTransactionBook(account.getClientId()).getTransactions()
                    .add(new Transaction(date.getDateNow(), account.getCurrency(),"Deposit",quantityDouble));
        }
    }

    public void withdrawal(){
        Account account;

        while (true){
            System.out.println("Please enter account ID to withdrawal");
            String id = keypad.getString();
            while(!id.matches("[0-9]+")){
                System.out.println("Please enter a valid account ID");
                id = keypad.getString();
            }
            Integer idInt = Integer.parseInt(id);
            account = getAccount(idInt);
            if(account != null){
                break;
            }
            System.out.println("id incorrect");
        }
        if(account !=null){
            while (true){
                // if 5 mins
                if(account.getSnapshotTime()>0 && timer.timeAgo(account.getSnapshotTime())<300){
                    System.out.println("not allow to withdrawal. Wait more "+
                            (300 - timer.timeAgo(account.getSnapshotTime()))+
                                    " seconds");
                    break;
                }
                if(account.getSnapshotTime()> 0 && timer.timeAgo(account.getSnapshotTime())>300){
                    account.NumberOfWithdrawalToZero();
                    account.setSnapshotTime(0);
                }
                System.out.println("Please enter amount of money ("+account.getCurrency()+")");
                String quantity = keypad.getString();
                while(!quantity.matches("[0-9]+")){
                    System.out.println("Please enter a valid amount of money");
                    quantity = keypad.getString();
                }
                Double quantityDouble = Double.parseDouble(quantity);
                if (account.getBalance() >= quantityDouble + quantityDouble * 0.01){
                    account.setBalance(account.getBalance() - quantityDouble - quantityDouble * 0.01);
                    transactionBookResource.getTransactionBook(account.getClientId()).getTransactions()
                            .add(new Transaction(date.getDateNow(),
                                    account.getCurrency(),
                                    "Withdrawal",
                                    quantityDouble));
                    OSLhandlingFee(quantityDouble, account.getCurrency());
                    transactionBookResource.getTransactionBook(account.getClientId()).getTransactions()
                            .add(new Transaction(date.getDateNow(),
                                    account.getCurrency(),
                                    "Withdrawal FEE",
                                    quantityDouble*0.01));
                    account.increaseNumberOfWithdrawal();


                    // when user withdrawal fifth time
                    if(account.getNumberOfWithdrawal()==5 && account.getSnapshotTime() == 0){
                        Date snapshotTime = new Date();
                        timer.setSnapshotTime(snapshotTime.getTime());
                        account.setSnapshotTime(timer.getSnapshotTime());
                        System.out.println("not allow to withdrawal. Wait more "+
                                (300 - timer.timeAgo(account.getSnapshotTime()))+
                                " seconds");
                        break;
                    }
                    break;
                }
                System.out.println("insufficient funds in your account");
            }
        }

    }

    public void OSLhandlingFee(Double quantity, String currency){
        switch (currency){
            case "USD":
                Account oslAccountUSD = getAccount(9999);
                oslAccountUSD.setBalance(oslAccountUSD.getBalance()+quantity*0.01);
                break;
            case "HKD":
                Account oslAccountHKD = getAccount(9998);
                oslAccountHKD.setBalance(oslAccountHKD.getBalance()+quantity*0.01);
                break;
            case "SGD":
                Account oslAccountSGD = getAccount(9997);
                oslAccountSGD.setBalance(oslAccountSGD.getBalance()+quantity*0.01);
                break;
        }
    }

    public void transfer(){
        Account account;
        System.out.println("Payor account ID?");
        String payorIdStr = keypad.getString();
        while(!payorIdStr.matches("[0-9]+")){
            System.out.println("Please enter a valid payer's account ID!");
            payorIdStr = keypad.getString();
        }
        Integer payorId = Integer.parseInt(payorIdStr);

        System.out.println("Payee account ID?");
        String payeeIdStr = keypad.getString();
        while(!payeeIdStr.matches("[0-9]+")){
            System.out.println("Please enter a valid payee's account ID!");
            payeeIdStr = keypad.getString();
        }
        Integer payeeId = Integer.parseInt(payeeIdStr);

        Account payorAccount = getAccount(payorId);
        Account payeeAccount = getAccount(payeeId);

        if(payorAccount != null && payeeAccount != null
                && payorAccount.getCurrency().equals(payeeAccount.getCurrency())){
            System.out.println("Please enter amount of money ("+payorAccount.getCurrency()+")");
            String quantity = keypad.getString();
            while(!quantity.matches("[0-9]+")){
                System.out.println("Please enter a valid amount of money");
                quantity = keypad.getString();
            }
            Double quantityDouble = Double.parseDouble(quantity);
            payorAccount.setBalance(payorAccount.getBalance()-quantityDouble-quantityDouble*0.01);
            transactionBookResource.getTransactionBook(payorAccount.getClientId()).getTransactions()
                    .add(new Transaction(date.getDateNow(), payorAccount.getCurrency(),"Transfer out",quantityDouble));
            transactionBookResource.getTransactionBook(payorAccount.getClientId()).getTransactions()
                    .add(new Transaction(date.getDateNow(), payorAccount.getCurrency(),"Transfer FEE",quantityDouble*0.01));
            payeeAccount.setBalance(payeeAccount.getBalance()+quantityDouble);
            transactionBookResource.getTransactionBook(payeeAccount.getClientId()).getTransactions()
                    .add(new Transaction(date.getDateNow(), payeeAccount.getCurrency(),"Transfer In",quantityDouble));
            OSLhandlingFee(quantityDouble, payorAccount.getCurrency());

        }else{
            System.out.println("The account ID is invalid or theirs currency does not match");
        }

    }



    public void saveDefaultAccount(){
        Account account_1 = new Account("Peter", "USD", 1000, 8080,"y3");
        Account account_2 = new Account("Alan", "USD", 500, 9090,"f32");
        Account account_3 = new Account("Steven", "HKD", 1500, 1010,"r43");
        Account account_4 = new Account("OSL_FEE_USD","USD",0,9999,"NA");
        Account account_5 = new Account("OSL_FEE_HKD","HKD",0,9998,"NA");
        Account account_6 = new Account("OSL_FEE_SGD","SGD",0,9997,"NA");
        Account account_7 = new Account("Peter", "HKD", 2000, 8081, "y3");
        Account account_8 = new Account("Peter", "SGD", 2000, 8082, "y3");
        accountDAOService.save(account_1);
        accountDAOService.save(account_2);
        accountDAOService.save(account_3);
        accountDAOService.save(account_4);
        accountDAOService.save(account_5);
        accountDAOService.save(account_6);
        accountDAOService.save(account_7);
        accountDAOService.save(account_8);
    }
    public void creatAccount(){
        System.out.println("Username?");
        String userName = keypad.getString();
        while(!userName.matches("[a-zA-Z]+")){
            System.out.println("Please enter a valid name");
            userName = keypad.getString();
        }
        System.out.println("Currency?");
        String currency = keypad.getString();
        while(!currency.matches("USD") && !currency.matches("HKD") && !currency.matches("SGD")){
            System.out.println("Please enter a valid currency");
            currency = keypad.getString();
        }

        System.out.println("ID card number?");
        String idString = keypad.getString();
        while(!idString.matches("[a-zA-Z0-9]+")){
            System.out.println("Please enter a valid id number");
            idString = keypad.getString();
        }
        if(checkValidUserToCreateAccount(idString, currency)){
            Account account = new Account(userName, currency, 0, id++, idString);
            if(transactionBookResource.getTransactionBook(idString)==null){
                TransactionBook transactionBook = new TransactionBook(idString);
                transactionBookResource.saveTransactionBook(transactionBook);
                System.out.println("Transaction Book created");

            }
            accountDAOService.save(account);
            System.out.println("Account created");

        }
    }



    public boolean checkValidUserToCreateAccount(String clientId, String currency){
        Account account = accountDAOService.findOneByClientId(clientId);
        if(account == null|| (account!=null && !account.getCurrency().equals(currency))){
            return true;
        }
        List<Account> accounts = getAllAccounts();
        for (Account currentAccount : accounts){
            if(currentAccount.getClientId().equals(clientId) && currentAccount.getCurrency().equals(currency)){
                System.out.println("Your account has been exited.");
                return false;
            }
        }
        return true;
    }




}
