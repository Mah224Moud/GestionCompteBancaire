package com.gestion.compte.bancaire.customer;

import com.gestion.compte.bancaire.models.CommonModel;
import com.gestion.compte.bancaire.models.CustomerModel;
import com.gestion.compte.bancaire.utils.Action;
import com.gestion.compte.bancaire.utils.User;
import com.gestion.compte.bancaire.utils.Utils;

public class Customer extends User implements Action {
    private String type;
    private int accountNumber;
    private int bankerId;

    CustomerModel customerModel;

    public Customer(String email, String password) {
        super(0, "", "", "", "", "", "", "");
        this.customerModel = new CustomerModel();
        Customer customer = customerModel.getCustomer(email, password);
        this.setId(customer.getBankerId());
        this.setName(customer.getName());
        this.setFirstname(customer.getFirstname());
        this.setGender(customer.getGender());
        this.setAddress(customer.getAddress());
        this.setPhone(customer.getPhone());
        this.setType(customer.getType());
        this.setAccountNumber(customer.getAccountNumber());
        this.setBankerId(customer.getBankerId());
        this.setEmail(customer.getEmail());
    }

    public Customer(int id, String name, String firstname, String gender, String address, String phone, String type,
            int accountNumber, int bankerId, String email, String password) {
        super(id, name, firstname, gender, address, phone, email, password);
        this.type = type;
        this.accountNumber = accountNumber;
        this.bankerId = bankerId;
    }

    public Customer(int id, String name, String firstname, String gender, String address, String phone, String type,
            int accountNumber, int bankerId, String email) {
        super(id, name, firstname, gender, address, phone, email, "");
        this.type = type;
        this.accountNumber = accountNumber;
        this.bankerId = bankerId;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(int account_number) {
        this.accountNumber = account_number;
    }

    public int getBankerId() {
        return this.bankerId;
    }

    public void setBankerId(int bankerId) {
        this.bankerId = bankerId;
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
                "Numero de compte: " + this.getAccountNumber() + "\n" +
                "*********************************************\n" +
                "*********************************************\n";
    }

    @Override
    public void withdraw(double amount) throws Exception {
        if (!customerModel.withdraw(this.getAccountNumber(), amount)) {
            throw new Exception("Retrait Impossible.\n");
        }

        System.out.println("\nRetrait de " + Utils.formatAmount(amount) + "€ effectué avec succès.\n");
    }

    @Override
    public void deposit(double amount) throws Exception {
        if (!customerModel.deposit(this.getAccountNumber(), amount)) {
            throw new Exception("Depot Impossible.\n");
        }

        System.out.println("\nDepot de " + Utils.formatAmount(amount) + "€ effectfué avec succès.\n");
    }

    @Override
    public void seeBalance() {
        CommonModel commonModel = new CommonModel();
        System.out.println("\n" + this.getGender() + " " + this.getName() + ", le solde de votre compte n°"
                + this.getAccountNumber() + " est de: "
                + commonModel.getBalance(this.getAccountNumber()) + "\n");
    }

}