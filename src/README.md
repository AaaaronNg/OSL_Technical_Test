## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Assumption](#assumption)


## General info
This project is created for OSL Technical test (Problem Statement 2).


## Technologies
Project is created with:
* Java 18
* openJDK-18 version 18.0.1

## Assumption
User should know about their ID card number and accountID.
There is no limit to deposit. <br/>
ID card number that user enter is considered as a clientId. <br/>
Each account own a transaction book which jots down their its transaction.

## Remark
1. There are some default accounts. <br/>
Account_1: <br/>
userName: Peter, currency: USD, balance: 1000, accountId: 8080, clientId: y3 <br/>
Account_2: <br/>
userName: Alan, currency: USD, balance: 500, accountId: 9090, clientId: f32 <br/>
Account_3: <br/>
userName: Steven, currency: HKD, balance: 1500, accountId: 1010, clientId: r43 <br/>
Account_4: <br/>
userName: OSL_FEE, currency: USD, balance: 0, accountId: 9999, clientId: NA <br/>
Account_5: <br/>
userName: OSL_FEE, currency: HKD, balance: 0, accountId: 9998, clientId: NA <br/>
Account_6: <br/>
userName: OSL_FEE, currency: SGD, balance: 0, accountId: 9997, clientId: NA <br/>
Account_1: <br/>
userName: Peter, currency: HKD, balance: 2000, accountId: 8081, clientId: y3 <br/>
Account_1: <br/>
userName: OSL_FEE, currency: SGD, balance: 0, accountId: 8082, clientId: y3 <br/>

2. the created account of account ID will start from 0, and then its will increase by 1
,which means the first created account ID is 0, and the next one is 1.


