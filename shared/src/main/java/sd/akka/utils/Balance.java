package sd.akka.utils;

import java.io.Serializable;

public class Balance implements Serializable {

    private final int accountNumber;
    private final String message;

    public Balance(int accountNumber, String message) {
        this.accountNumber = accountNumber;
        this.message = message;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getMessage() {
        return message;
    }

}
