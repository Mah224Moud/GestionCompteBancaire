package com.gestion.compte.bancaire.accounts;

import com.gestion.compte.bancaire.banker.Banker;
import com.gestion.compte.bancaire.customers.Customer;

public class Account implements Action {
    private int accountId;
    private int balance;
    private int accountNumber;
    private String accountStatus = "active";
    private Customer customer;
    private Banker banker;

    public Account(int accountId, int balance, int accountNumber, String accountStatus, Customer customer,
            Banker banker) {
        this.accountId = accountId;
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.accountStatus = accountStatus;
        this.customer = customer;
        this.banker = banker;
    }

    public int getAccountId() {
        return this.accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getBalance() {
        return this.balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountStatus() {
        return this.accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Banker getBanker() {
        return this.banker;
    }

    public void setBanker(Banker banker) {
        this.banker = banker;
    }

    @Override
    public String toString() {
        return "Bonjour, " + this.customer.getFirstname() + " " + this.customer.getFirstname() + ". Votre compte "
                + this.getAccountNumber()
                + " est "
                + this.getAccountStatus()
                + ". Votre solde est de "
                + this.getBalance()
                + "€";
    }

    @Override
    public void withdraw(double amount) {
        this.balance -= amount;
    }

    @Override
    public void deposit(double amount) {
        this.balance += amount;
    }

    @Override
    public void seeBalance() {
        System.out.println(this.toString());
    }
}
