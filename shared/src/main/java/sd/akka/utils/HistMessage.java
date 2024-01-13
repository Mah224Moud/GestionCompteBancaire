package sd.akka.utils;

import java.io.Serializable;

public class HistMessage implements Serializable {

    private String message;
    private int accountNumber;

    public HistMessage(String message, int accountNumber) {
        this.message = message;
        this.accountNumber = accountNumber;
    }

    /**
     * Retrieves the message stored in the 'message' field.
     *
     * @return the message string.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Returns the account number.
     *
     * @return the account number
     */
    public int getAccountNumber() {
        return accountNumber;
    }
}
