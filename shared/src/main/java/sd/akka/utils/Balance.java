package sd.akka.utils;

import java.io.Serializable;

public class Balance implements Serializable {

    private final int accountNumber;
    private final String message;

    public Balance(int accountNumber, String message) {
        this.accountNumber = accountNumber;
        this.message = message;
    }

    /**
     * Retrieves the account number associated with this object.
     *
     * @return the account number
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * Gets the message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

}
