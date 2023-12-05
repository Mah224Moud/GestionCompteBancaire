package com.gestion.compte.bancaire.customers;

import com.gestion.compte.bancaire.User;

public class Customer extends User {
    private String type;

    public Customer(int id, String name, String firstname, String gender, String address, String phone, String type) {
        super(id, name, firstname, gender, address, phone);
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.getGender() + " " + this.getName() + " " + this.getFirstname();
    }

}
