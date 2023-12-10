package com.gestion.compte.bancaire.account;

import com.gestion.compte.bancaire.banker.Banker;
import com.gestion.compte.bancaire.customer.Customer;

public class Account {
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
}
