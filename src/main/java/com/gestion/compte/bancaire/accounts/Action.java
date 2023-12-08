package com.gestion.compte.bancaire.accounts;

public interface Action {
    public void withdraw(double amount) throws Exception;

    public void deposit(double amount) throws Exception;

    public void seeBalance();
}
