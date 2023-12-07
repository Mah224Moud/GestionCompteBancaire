package com.gestion.compte.bancaire.customers;

import com.gestion.compte.bancaire.User;

public class Customer extends User {
    private String type;
    private int accountNumber;
    private int bankerId;

    public Customer(int id, String name, String firstname, String gender, String address, String phone, String type,
            int accountNumber, int bankerId, String email, String password) {
        super(id, name, firstname, gender, address, phone, email, password);
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
        return this.getGender() + " " + this.getName() + " " + this.getFirstname();
    }

}
