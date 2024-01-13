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

    public Logged(String email, String name, String firstName, String gender, String address, String phone,
            String type) {
        this.email = email;
        this.name = name;
        this.firstName = firstName;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.type = type;
    }

    /**
     * Returns the email associated with this object.
     *
     * @return the email associated with this object.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Retrieves the name of the object.
     *
     * @return the name of the object
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Retrieves the gender of the entity.
     *
     * @return the gender of the entity
     */
    public String getGender() {
        return gender;
    }

    /**
     * Get the address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Retrieves the phone number associated with this object.
     *
     * @return the phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * A description of the entire Java function.
     *
     * @param paramName description of parameter
     * @return description of return value
     */
    public String getType() {
        return type;
    }

    /**
     * Returns a string representation of the object. The returned string includes
     * a header with the type of the object and a footer with contact information.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "\n" +
                "*********************************************\n" +
                "************* NOUVELLE CONNEXION ************\n" +
                "***************** " + this.getType() + " *****************\n" +
                "*********************************************\n" +
                this.getGender() + " " + this.getName() + " " + this.getFirstName() + "\n\n" +
                "Contact: " + this.getPhone() + ", \n" +
                "Email: " + this.getEmail() + ", \n" +
                "Adresse: " + this.getAddress() + "\n" +
                "*********************************************\n" +
                "*********************************************\n";
    }

}
