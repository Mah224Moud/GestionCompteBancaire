package com.gestion.compte.bancaire.utils;

public class Transaction {
    private final String message;
    private final double amount;
    private final int accountNumber;

    public Transaction(String message, double amount, int accountNumber) {
        this.message = message;
        this.amount = amount;
        this.accountNumber = accountNumber;
    }

    public String getMessage() {
        return message;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getAmount() {
        return amount;
    }
}
