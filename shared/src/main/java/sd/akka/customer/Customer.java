package sd.akka.customer;

import sd.akka.database.DatabaseManager;
import sd.akka.models.CommonModel;
import sd.akka.models.CustomerModel;
import sd.akka.utils.User;

public class Customer extends User {
    private String type;
    private int accountNumber;
    private int bankerId;

    private CustomerModel customerModel;
    DatabaseManager databaseManager;

    public Customer(String email, String password) {
        super(0, "", "", "", "", "", "", "");
        this.databaseManager = new DatabaseManager();
        this.customerModel = new CustomerModel(databaseManager);
        Customer customer = customerModel.getCustomer(email, password);
        this.setId(customer.getBankerId());
        this.setName(customer.getName());
        this.setFirstname(customer.getFirstname());
        this.setGender(customer.getGender());
        this.setAddress(customer.getAddress());
        this.setPhone(customer.getPhone());
        this.setType(customer.getType());
        this.setAccountNumber(customer.getAccountNumber());
        this.setBankerId(customer.getBankerId());
        this.setEmail(customer.getEmail());
    }

    public Customer(int id, String name, String firstname, String gender, String address, String phone, String type,
            int accountNumber, int bankerId, String email, String password) {
        super(id, name, firstname, gender, address, phone, email, password);
        this.type = type;
        this.accountNumber = accountNumber;
        this.bankerId = bankerId;
    }

    public Customer(int id, String name, String firstname, String gender, String address, String phone, String type,
            int accountNumber, int bankerId, String email) {
        super(id, name, firstname, gender, address, phone, email, "");
        this.type = type;
        this.accountNumber = accountNumber;
        this.bankerId = bankerId;
    }

    /**
     * Retrieves the type of the object.
     *
     * @return The type of the object.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Sets the type of the object.
     *
     * @param type the new type for the object
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Retrieves the account number associated with this object.
     *
     * @return the account number
     */
    public int getAccountNumber() {
        return this.accountNumber;
    }

    /**
     * Sets the account number for the object.
     *
     * @param accountNumber the new account number to be set
     */
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Retrieves the banker ID.
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

    /**
     * A description of the entire Java function.
     *
     * @Override
     * @return description of return value
     */
    @Override
    public String toString() {
        return "\n" +
                "*********************************************\n" +
                "************** INFORMATIONS *****************\n" +
                "*********************************************\n" +
                "Bonjour, " + this.getGender() + " " + this.getName() + " " + this.getFirstname() + "\n\n" +
                "Contact: " + this.getPhone() + ", \n" +
                "Email: " + this.getEmail() + ", \n" +
                "Numero de compte: " + this.getAccountNumber() + "\n" +
                "*********************************************\n" +
                "*********************************************\n";
    }

    /**
     * Prints the balance of the account for the current user.
     *
     * @param None
     * @return None
     */
    public void seeBalance() {
        CommonModel commonModel = new CommonModel(databaseManager);
        System.out.println("\n" + this.getGender() + " " + this.getName() + ", le solde de votre compte n°"
                + this.getAccountNumber() + " est de: "
                + commonModel.getBalance(this.getAccountNumber()) + "\n");
    }

}
