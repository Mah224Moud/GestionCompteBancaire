package com.gestion.compte.bancaire.banker;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import com.gestion.compte.bancaire.User;
import com.gestion.compte.bancaire.models.Model;
import com.gestion.compte.bancaire.accounts.Account;
import com.gestion.compte.bancaire.accounts.Action;
import com.gestion.compte.bancaire.customers.Customer;

public class Banker extends User implements Action {
    private String position;
    Model bankerModel = new Model();

    public Banker(int id) {
        super(0, "", "", "", "", "");
        Banker banker = bankerModel.getBankerById(id);

        this.setId(banker.getId());
        this.setName(banker.getName());
        this.setFirstname(banker.getFirstname());
        this.setGender(banker.getGender());
        this.setAddress(banker.getAddress());
        this.setPhone(banker.getPhone());
        this.setPosition(banker.getPosition());

    }

    public Banker(int id, String name, String firstname, String gender, String address, String phone, String position) {
        super(id, name, firstname, gender, address, phone);
        this.position = position;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    private String formatAmount(double amount) {
        DecimalFormat df = new DecimalFormat("#,###.00", new DecimalFormatSymbols(Locale.US));
        return df.format(amount) + "€";
    }

    @Override
    public void withdraw(int accountNumber, double amount) {
        if (bankerModel.withdraw(accountNumber, amount)) {
            System.out.println("Retrait de " + formatAmount(amount) + "€ effectué avec succès.\n");
        } else {
            System.out.println("Retrait Impossible.\n");
        }
    }

    @Override
    public void deposit(int accountNumber, double amount) {
        if (bankerModel.deposit(accountNumber, amount)) {
            System.out.println("Depot de " + formatAmount(amount) + "€ effectfué avec succès.\n");
        } else {
            System.out.println("Depot Impossible.\n");
        }
    }

    @Override
    public void seeBalance(int accountNumber) {
        System.out.println("Le solde du compte n°" + accountNumber + " est de "
                + bankerModel.getBalance(accountNumber)
                + "€");
    }

    @Override
    public String toString() {
        return this.getGender() + " " + this.getName() + " " + this.getFirstname() + ", \n" +
                "Contact: " + this.getPhone();
    }

    public void addCustomer(Customer customer) {
        int accountNumber = bankerModel.getLastAccountNumber() + 1;
        int customerId = bankerModel.getLastCustomerId() + 1;

        customer.setAccountNumber(accountNumber);
        customer.setId(customerId);

        Account account = new Account(0, 0.0, accountNumber, customerId, this.getId());

        boolean successCustomer = bankerModel.addCustomer(customer);
        boolean successAccount = bankerModel.addAccount(account);
        if (successAccount && successCustomer) {
            System.out.println(
                    "***************************************************\n" +
                            "********************* AJOUT ***********************\n" +
                            "***************************************************\n");

            System.out.println(customer.toString() + " a été ajouté(e) avec succes.");
            System.out.println("Le compte n° " + accountNumber + " a été ajouté avec succes.\n");
        } else {
            System.out.println("Problème survenue lors de l'ajout veuillez reessayer.\n");
        }
    }

}
