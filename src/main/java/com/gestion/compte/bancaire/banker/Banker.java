package com.gestion.compte.bancaire.banker;

import com.gestion.compte.bancaire.User;
import com.gestion.compte.bancaire.models.CommonModel;
import com.gestion.compte.bancaire.accounts.Account;
import com.gestion.compte.bancaire.customers.Customer;
import com.gestion.compte.bancaire.models.BankerModel;

public class Banker extends User {
    private String position;

    BankerModel bankerModel;

    public Banker(String email, String password) {
        super(0, "", "", "", "", "", "", "");
        this.bankerModel = new BankerModel();
        Banker banker = bankerModel.getBanker(email, password);

        this.setId(banker.getId());
        this.setName(banker.getName());
        this.setFirstname(banker.getFirstname());
        this.setGender(banker.getGender());
        this.setAddress(banker.getAddress());
        this.setPhone(banker.getPhone());
        this.setPosition(banker.getPosition());
        this.setEmail(banker.getEmail());

    }

    public Banker(int id, String name, String firstname, String gender, String address, String phone, String position,
            String email, String password) {
        super(id, name, firstname, gender, address, phone, email, password);
        this.position = position;
    }

    public Banker(int id, String name, String firstname, String gender, String address, String phone, String position,
            String email) {
        super(id, name, firstname, gender, address, phone, email, "");
        this.position = position;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void seeBalance(int accountNumber) {
        CommonModel commonModel = new CommonModel();
        System.out.println("Le solde du compte n°" + accountNumber + " est de "
                + commonModel.getBalance(accountNumber)
                + "€");
    }

    public void addCustomer(Customer customer) throws Exception {
        int accountNumber = generateAccountNumber();
        int customerId = generateCustomerId();

        customer.setAccountNumber(accountNumber);
        customer.setId(customerId);

        Account account = new Account(0, 0.0, accountNumber, customerId, this.getId());

        boolean successCustomer = addCustomerToDatabase(customer);
        boolean successAccount = addAccountToDatabase(account);

        if (successAccount && successCustomer) {
            displaySuccessMessage(customer, accountNumber);
        } else {
            throw new Exception("Problème survenue lors de l'ajout veuillez reessayer.\n");
        }
    }

    private int generateAccountNumber() {
        return bankerModel.getLastAccountNumber() + 1;
    }

    private int generateCustomerId() {
        return bankerModel.getLastCustomerId() + 1;
    }

    private boolean addCustomerToDatabase(Customer customer) {
        return bankerModel.addCustomer(customer);
    }

    private boolean addAccountToDatabase(Account account) {
        return bankerModel.addAccount(account);
    }

    private void displaySuccessMessage(Customer customer, int accountNumber) {
        System.out.println(
                "\n" +
                        "***************************************************\n" +
                        "********************* AJOUT ***********************\n" +
                        "***************************************************\n");

        System.out.println(customer.toString() + " a été ajouté(e) avec succes.");
        System.out.println("Le compte n° " + accountNumber + " a été ajouté avec succes.\n");
    }

    @Override
    public String toString() {
        return "\n" +
                "*********************************************\n" +
                "************** INFORMATIONS *****************\n" +
                "*********************************************\n" +
                "Bonjour, " + this.getGender() + " " + this.getName() + " " + this.getFirstname() + "\n\n" +
                "Contact: " + this.getPhone() + ", \n" +
                "Email: " + this.getEmail() + ", \n" +
                "Position: " + this.getPosition() + "\n" +
                "*********************************************\n" +
                "*********************************************\n";
    }
}
