package sd.akka.utils;

import java.io.Serializable;

public class Message implements Serializable {
    private final String message;
    private final double amount;

    public Message(String message, double amount) {
        this.message = message;
        this.amount = amount;
    }

    /**
     * Retrieves the message stored in the object.
     *
     * @return The message stored in the object as a string.
     */
    public String getMessage() {
        return message;
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
