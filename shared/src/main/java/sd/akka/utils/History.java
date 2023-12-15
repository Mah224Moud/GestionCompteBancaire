package sd.akka.utils;

public class History {
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

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getAccoutNumber() {
        return this.accountNumber;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return this.getType() + " de " + Utils.formatAmount(this.getAmount()) + " sur votre compte n°"
                + this.getAccoutNumber() + " le " + this.getDate();
    }
}
