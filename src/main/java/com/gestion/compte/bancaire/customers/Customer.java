package com.gestion.compte.bancaire.customers;

import com.gestion.compte.bancaire.User;

public class Customer extends User {
    private String type;
    private String phone;

    public Customer(int id, String name, String firstname, String address, String type, String phone) {
        super(id, name, firstname, address);
        this.type = type;
        this.phone = phone;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
