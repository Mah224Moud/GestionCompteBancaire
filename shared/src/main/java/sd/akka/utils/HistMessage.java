package sd.akka.utils;

import java.io.Serializable;

public class HistMessage implements Serializable {

    private String message;
    private int accountNumber;

    public HistMessage(String message, int accountNumber) {
        this.message = message;
        this.accountNumber = accountNumber;
    }

    public String getMessage() {
        return message;
    }

    public int getAccountNumber() {
        return accountNumber;
    }
}
