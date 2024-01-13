package sd.akka.account;

public class Account {
    private int id;
    private double balance;
    private int number;
    private String status;
    private int customerId;
    private int bankerId;

    public Account(int id, double balance, int number, int customerId, int bankerId) {
        this.id = id;
        this.balance = balance;
        this.number = number;
        this.status = "actif";
        this.customerId = customerId;
        this.bankerId = bankerId;
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
     * Sets the ID of the object.
     *
     * @param id the new ID value
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the current balance of the account.
     *
     * @return the current balance of the account
     */
    public double getBalance() {
        return this.balance;
    }

    /**
     * Sets the balance of the account.
     *
     * @param balance the new balance to set
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Retrieves the value of the number field.
     *
     * @return the value of the number field
     */
    public int getNumber() {
        return this.number;
    }

    /**
     * Sets the value of the number variable.
     *
     * @param number the new value for the number variable
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Retrieves the status of the object.
     *
     * @return the current status of the object
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * Sets the status of the object.
     *
     * @param status the new status to be set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Retrieves the customer ID.
     *
     * @return the customer ID
     */
    public int getCustomerId() {
        return this.customerId;
    }

    /**
     * Sets the customer ID.
     *
     * @param customerId the ID of the customer
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Returns the banker ID.
     *
     * @return the banker ID
     */
    public int getBankerId() {
        return this.bankerId;
    }

    /**
     * Sets the banker ID.
     *
     * @param bankerId the ID of the banker
     */
    public void setBankerId(int bankerId) {
        this.bankerId = bankerId;
    }
}
