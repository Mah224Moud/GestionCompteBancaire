package sd.akka.utils;

import java.io.Serializable;

public class Logged implements Serializable {
    private String email;
    private String name;
    private String firstName;
    private String gender;
    private String address;
    private String phone;
    private String type;

    public Logged(String email, String name, String firstName, String gender, String address, String phone, String type) {
        this.email = email;
        this.name = name;
        this.firstName = firstName;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "\n" +
                "*********************************************\n" +
                "************* NOUVELLE CONNEXION ************\n" +
                "***************** " + this.getType() + " *****************\n"+
                "*********************************************\n" +
                this.getGender() + " " + this.getName() + " " + this.getFirstName() + "\n\n" +
                "Contact: " + this.getPhone() + ", \n" +
                "Email: " + this.getEmail() + ", \n" +
                "Adresse: " + this.getAddress() + "\n" +
                "*********************************************\n" +
                "*********************************************\n";
    }

}
