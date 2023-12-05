package com.gestion.compte.bancaire.accounts;

public interface Action {
    public void withdraw(double amount);

    public void deposit(double amount);

    public void seeBalance();
}
