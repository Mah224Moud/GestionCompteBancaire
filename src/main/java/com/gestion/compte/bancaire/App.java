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
                0,
                "Martin",
                "Jean",
                "Mr",
                "123 Main Street",
                "555-555-5555",
                "individual",
                0,
                1);

        Banker banker = new Banker(1);
        banker.addCustomer(customer);
        System.out.println(banker.toString());
        banker.seeBalance(245860);

        banker.deposit(245860, 1000);

        banker.seeBalance(245860);
        banker.withdraw(245860, 50);
        banker.seeBalance(245860);

    }
}
