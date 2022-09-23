package org.example;

import java.util.ArrayList;
import java.util.List;

public class AccountDAOService {
    private static List<Account> accounts = new ArrayList<>();

    public Account findOneByClientId(String clientId){
        try{
            for(Account account: accounts){
                if(account.getClientId().equals(clientId)){
                    return account;
                }
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }
    public List<Account> findAll(){
        return accounts;
    }
    public Account findOne(int id){
        return accounts.stream().filter(account -> account.getAccountId().equals(id))
                .findFirst().orElse(null);
    }

    public void save(Account account){
        accounts.add(account);
    }
}
