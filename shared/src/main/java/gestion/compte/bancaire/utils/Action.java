package gestion.compte.bancaire.utils;

public interface Action {
    public String withdraw(double amount, int accountNumber) throws Exception;

    public String deposit(double amount, int accountNumber) throws Exception;

    public void seeBalance(int accountNumber);
}
