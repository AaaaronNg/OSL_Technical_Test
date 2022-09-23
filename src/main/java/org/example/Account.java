package org.example;

public class Account {
    private String userName;
    private String currency;
    private double balance;

    private Integer accountId;
    private String clientId;
    private int NumberOfWithdrawal;
    private long snapshotTime;

    public Account(String userName, String currency, double balance, Integer accountId, String clientId) {
        this.userName = userName;
        this.currency = currency;
        this.balance = balance;
        this.accountId = accountId;
        this.clientId = clientId;
        this.NumberOfWithdrawal = 0;
        this.snapshotTime = 0L;
    }

    public Account(String userName, String currency, double balance, Integer accountId) {
        this.userName = userName;
        this.currency = currency;
        this.balance = balance;
        this.accountId = accountId;
    }

    public Account() {
    }

    public void increaseNumberOfWithdrawal(){
        this.NumberOfWithdrawal++;
    }
    public void NumberOfWithdrawalToZero(){
        this.NumberOfWithdrawal = 0;
    }
    public int getNumberOfWithdrawal() {
        return NumberOfWithdrawal;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setSnapshotTime(long snapshotTime) {
        this.snapshotTime = snapshotTime;
    }

    public long getSnapshotTime() {
        return snapshotTime;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getUserName() {
        return userName;
    }

    public String getCurrency() {
        return currency;
    }

    public double getBalance() {
        return balance;
    }
}
