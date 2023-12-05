package com.gestion.compte.bancaire;

import com.gestion.compte.bancaire.banker.Banker;
import com.gestion.compte.bancaire.customers.Customer;
import com.gestion.compte.bancaire.accounts.Account;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Customer customer = new Customer(
                1,
                "John",
                "Doe",
                "Mr",
                "123 Main Street",
                "123-456-7890",
                "individual");
        Banker banker = new Banker(
                2,
                "Jane",
                "Doe",
                "Mme",
                "456 Second Street",
                "123-456-7890",
                "manager");

        Account account = new Account(
                1,
                1000,
                123456,
                customer,
                banker);

        account.info();
        account.seeBalance();
        account.withdraw(500);
        account.seeBalance();
        account.deposit(10500);
        account.seeBalance();
    }
}
