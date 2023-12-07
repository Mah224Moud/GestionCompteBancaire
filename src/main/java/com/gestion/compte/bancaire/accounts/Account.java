package com.gestion.compte.bancaire.accounts;

import com.gestion.compte.bancaire.banker.Banker;
import com.gestion.compte.bancaire.customers.Customer;

public class Account implements Action {
    private int id;
    private double balance;
    private int number;
    private String status;
    private int customerId;
    private int bankerId;
    private Customer customer;
    private Banker banker;

    public Account(int id, double balance, int number, int customerId, int bankerId) {
        this.id = id;
        this.balance = balance;
        this.number = number;
        this.status = "actif";
        this.customerId = customerId;
        this.bankerId = bankerId;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public int getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getBankerId() {
        return this.bankerId;
    }

    public void setBankerId(int bankerId) {
        this.bankerId = bankerId;
    }

    public void info() {
        System.out.println(
                "***************************************************\n" +
                        "****************** INFORMATION ********************\n" +
                        "***************************************************\n" +
                        "Bonjour " + this.getCustomer().toString() +
                        "\nVotre compte numéro " + this.getNumber() + " est " + this.getStatus() + "." +
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
