package com.gestion.compte.bancaire;

public class User {
    private int id;
    private String name;
    private String firstname;
    private String address;

    public User(int id, String name, String firstname, String address) {
        this.id = id;
        this.name = name;
        this.firstname = firstname;
        this.address = address;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name.toUpperCase();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
