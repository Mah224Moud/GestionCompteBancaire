package com.gestion.compte.bancaire;

public class User {
    private int id;
    private String name;
    private String firstname;
    private String gender;
    private String address;
    private String phone;

    public User(int id, String name, String firstname, String gender, String address, String phone) {
        this.id = id;
        this.name = name;
        this.firstname = firstname;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
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

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
