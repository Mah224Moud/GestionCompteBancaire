package sd.akka.utils;

import java.io.Serializable;

public class Transaction implements Serializable {
    private final String message;
    private final double amount;
    private final int accountNumber;

    public Transaction(String message, double amount, int accountNumber) {
        this.message = message;
        this.amount = amount;
        this.accountNumber = accountNumber;
    }

    /**
     * Retrieves the message stored in the object.
     *
     * @return the message stored in the object
     */
    public String getMessage() {
        return message;
    }

    /**
     * Retrieves the account number.
     *
     * @return the account number
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * Retrieves the amount.
     *
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }
}
