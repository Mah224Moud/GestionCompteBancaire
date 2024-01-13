package sd.akka.utils;

import java.io.Serializable;

public class History implements Serializable {
    private int id;
    private int accountNumber;
    private double amount;
    private String type;
    private String date;

    public History(int id, int accountNumber, double amount, String type, String date) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.accountNumber = accountNumber;
        this.date = date;
    }

    /**
     * Sets the id of the object.
     *
     * @param id the new id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the ID of the object.
     *
     * @return the ID of the object
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets the account number for the object.
     *
     * @param accountNumber the account number to be set
     */
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Retrieves the account number associated with this object.
     *
     * @return the account number
     */
    public int getAccoutNumber() {
        return this.accountNumber;
    }

    /**
     * Sets the amount of the object.
     *
     * @param amount the new amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Retrieves the amount.
     *
     * @return the amount
     */
    public double getAmount() {
        return this.amount;
    }

    /**
     * Sets the type of the object.
     *
     * @param type the new type to be set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Retrieves the type of the object.
     *
     * @return the type of the object
     */
    public String getType() {
        return this.type;
    }

    /**
     * Sets the date for the object.
     *
     * @param date the new date to be set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Retrieves the date.
     *
     * @return the date value
     */
    public String getDate() {
        return this.date;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return this.getType() + " de " + Utils.formatAmount(this.getAmount()) + " sur votre compte n°"
                + this.getAccoutNumber() + " le " + this.getDate();
    }
}
