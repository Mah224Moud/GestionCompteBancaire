package com.gestion.compte.bancaire.accounts;

public interface Action {
    public void withdraw(int accountNumber, double amount);

    public void deposit(int accountNumber, double amount);

    public void seeBalance(int accountNumber);
}
