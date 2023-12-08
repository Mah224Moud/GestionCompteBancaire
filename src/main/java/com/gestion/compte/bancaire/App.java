package com.gestion.compte.bancaire;

import com.gestion.compte.bancaire.banker.Banker;
import com.gestion.compte.bancaire.customers.Customer;
import com.gestion.compte.bancaire.accounts.Account;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws Exception {

        Banker banker = new Banker("banquier@test.com", "banquier");
        System.out.println(banker.toString());
        Customer customer = new Customer("client1@test.com", "client1");
        System.out.println(customer.toString());
        customer.seeBalance();
        customer.deposit(5000);
        customer.seeBalance();
        customer.withdraw(2500);
        customer.seeBalance();

    }
}
