package com.gestion.compte.bancaire.banker;

import com.gestion.compte.bancaire.User;

public class Banker extends User {
    private String position;

    public Banker(int id, String name, String firstname, String address, String position) {
        super(id, name, firstname, address);
        this.position = position;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

}
