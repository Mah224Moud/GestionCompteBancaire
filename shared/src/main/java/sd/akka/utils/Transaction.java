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

    public String getMessage() {
        return message;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getAmount() {
        return amount;
    }
}
