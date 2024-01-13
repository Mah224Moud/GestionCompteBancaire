package sd.akka.utils;

public class User {
    private int id;
    private String name;
    private String firstname;
    private String gender;
    private String address;
    private String phone;
    private String email;
    private String password;

    public User(int id, String name, String firstname, String gender, String address, String phone, String email,
            String password) {
        this.id = id;
        this.name = name;
        this.firstname = firstname;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    /**
     * Returns the ID of the object.
     *
     * @return the ID of the object
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets the ID of the object.
     *
     * @param id the new ID for the object
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the name of the object in uppercase.
     *
     * @return the name of the object in uppercase
     */
    public String getName() {
        return this.name.toUpperCase();
    }

    /**
     * Set the name of the object.
     *
     * @param name the new name for the object
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the value of the firstname property.
     *
     * @return the value of the firstname property
     */
    public String getFirstname() {
        return this.firstname;
    }

    /**
     * Sets the value of the firstname attribute.
     *
     * @param firstname the new value for the firstname attribute
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Returns the gender of the object.
     *
     * @return the gender of the object
     */
    public String getGender() {
        return this.gender;
    }

    /**
     * Sets the gender of the object.
     *
     * @param gender the gender to be set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Returns the address of the object.
     *
     * @return the address of the object
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Sets the address of the object.
     *
     * @param address the new address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the phone number.
     *
     * @return the phone number
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * Sets the phone number for the object.
     *
     * @param phone the phone number to be set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Retrieves the email associated with this object.
     *
     * @return the email associated with this object
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Sets the email address.
     *
     * @param email the email address to be set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the password associated with this object.
     *
     * @return the password associated with this object
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets the password for the object.
     *
     * @param password the new password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
