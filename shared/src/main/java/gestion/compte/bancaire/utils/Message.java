package gestion.compte.bancaire.utils;

public class Message {
    private final String message;
    private final double amount;

    public Message(String message, double amount) {
        this.message = message;
        this.amount = amount;
    }

    public String getMessage() {
        return message;
    }

    public double getAmount() {
        return amount;
    }
}
