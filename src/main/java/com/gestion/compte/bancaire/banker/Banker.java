package com.gestion.compte.bancaire.banker;

import com.gestion.compte.bancaire.User;

public class Banker extends User {
    private String position;

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

    @Override
    public String toString() {
        return this.getGender() + " " + this.getName() + " " + this.getFirstname() + ", \n" +
                "Contact: " + this.getPhone();
    }

}
