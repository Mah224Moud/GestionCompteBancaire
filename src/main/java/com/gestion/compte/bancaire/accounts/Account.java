package com.gestion.compte.bancaire.accounts;

import com.gestion.compte.bancaire.banker.Banker;
import com.gestion.compte.bancaire.customers.Customer;

public class Account implements Action {
    private int accountId;
    private int balance;
    private int accountNumber;
    private String accountStatus;
    private Customer customer;
    private Banker banker;

    public Account(int accountId, int balance, int accountNumber, Customer customer,
            Banker banker) {
        this.accountId = accountId;
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.accountStatus = "actif";
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

    public void info() {
        System.out.println(
                "***************************************************\n" +
                        "****************** INFORMATION ********************\n" +
                        "***************************************************\n" +
                        "Bonjour " + this.getCustomer().toString() +
                        "\nVotre compte numéro " + this.getAccountNumber() + " est " + this.getAccountStatus() + "." +
                        "\nVotre solde est de " + this.getBalance() + "€" + "\n\n" +
                        "Votre conseiller.ère est: " + this.getBanker().toString() + "\n\n");

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
        System.out.println("Bonjour, " + this.getCustomer().toString() + " votre solde est de " + this.getBalance()
                + "€");
    }
}
