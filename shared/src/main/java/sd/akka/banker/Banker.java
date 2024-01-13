package sd.akka.banker;

import sd.akka.models.CommonModel;
import sd.akka.utils.User;
import sd.akka.utils.Utils;
import sd.akka.customer.Customer;
import sd.akka.database.DatabaseManager;
import sd.akka.models.BankerModel;

import java.util.List;

import sd.akka.account.Account;
import sd.akka.utils.Action;
import sd.akka.utils.History;

public class Banker extends User implements Action {
    private String position;

    BankerModel bankerModel;
    DatabaseManager databaseManager;

    public Banker(String email, String password) {
        super(0, "", "", "", "", "", "", "");
        this.databaseManager = new DatabaseManager();
        this.bankerModel = new BankerModel(databaseManager);
        Banker banker = bankerModel.getBanker(email, password);

        this.setId(banker.getId());
        this.setName(banker.getName());
        this.setFirstname(banker.getFirstname());
        this.setGender(banker.getGender());
        this.setAddress(banker.getAddress());
        this.setPhone(banker.getPhone());
        this.setPosition(banker.getPosition());
        this.setEmail(banker.getEmail());

    }

    public Banker(int id, String name, String firstname, String gender, String address, String phone, String position,
            String email, String password) {
        super(id, name, firstname, gender, address, phone, email, password);
        this.position = position;
    }

    public Banker(int id, String name, String firstname, String gender, String address, String phone, String position,
            String email) {
        super(id, name, firstname, gender, address, phone, email, "");
        this.position = position;
    }

    /**
     * Returns the position of the object.
     *
     * @return the position of the object
     */
    public String getPosition() {
        return this.position;
    }

    /**
     * Sets the position of the object.
     *
     * @param position the new position to set
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * See the balance of a specific account.
     *
     * @param accountNumber the account number of the account
     */
    @Override
    public void seeBalance(int accountNumber) {
        CommonModel commonModel = new CommonModel(databaseManager);
        System.out.println("\nLe solde du compte n°" + accountNumber + " est de "
                + commonModel.getBalance(accountNumber));
    }

    /**
     * Withdraws the specified amount from the given account number.
     *
     * @param amount        the amount to withdraw
     * @param accountNumber the account number to withdraw from
     * @return the success message if the withdrawal is successful
     * @throws Exception if the withdrawal is not possible
     */
    @Override
    public String withdraw(double amount, int accountNumber) throws Exception {
        if (!bankerModel.withdraw(accountNumber, amount)) {
            throw new Exception("Retrait Impossible.\n");
        }

        return "\nRetrait de " + Utils.formatAmount(amount) + " effectué avec succès.\n";
    }

    /**
     * Deposits the specified amount into the account with the given account number.
     *
     * @param amount        the amount to be deposited
     * @param accountNumber the account number of the account
     * @return a message indicating the success of the deposit
     * @throws Exception if the deposit is not possible
     */
    @Override
    public String deposit(double amount, int accountNumber) throws Exception {
        if (!bankerModel.deposit(accountNumber, amount)) {
            throw new Exception("Depot Impossible.\n");
        }

        return "\nDepot de " + Utils.formatAmount(amount) + " effectué avec succès.\n";
    }

    /**
     * Retrieves the histories associated with the given account number.
     *
     * @param accountNumber the account number for which to retrieve histories
     * @return a list of History objects representing the account's histories
     */
    @Override
    public List<History> getHistories(int accountNumber) {
        return bankerModel.getHistories(accountNumber);
    }

    /**
     * Adds a history to the system.
     *
     * @param history the history object to be added
     * @return true if the history was added successfully, false otherwise
     */
    @Override
    public boolean addHistory(History history) {
        return bankerModel.addHistory(history);
    }

    /**
     * Adds a customer to the system.
     *
     * @param customer the customer object to be added
     * @throws Exception if there is a problem adding the customer to the database
     */
    public void addCustomer(Customer customer) throws Exception {
        int accountNumber = generateAccountNumber();
        int customerId = generateCustomerId();

        customer.setAccountNumber(accountNumber);
        customer.setId(customerId);

        Account account = new Account(0, 0.0, accountNumber, customerId, this.getId());

        boolean successCustomer = addCustomerToDatabase(customer);
        boolean successAccount = addAccountToDatabase(account);

        if (successAccount && successCustomer) {
            displaySuccessMessage(customer, accountNumber);
        } else {
            throw new Exception("Problème survenue lors de l'ajout veuillez reessayer.\n");
        }
    }

    /**
     * Generates a new account number for a customer.
     *
     * @return the newly generated account number
     */
    private int generateAccountNumber() {
        return bankerModel.getLastAccountNumber() + 1;
    }

    /**
     * Generates a new customer ID by retrieving the last customer ID from the
     * banker model
     * and incrementing it by one.
     *
     * @return the new customer ID
     */
    private int generateCustomerId() {
        return bankerModel.getLastCustomerId() + 1;
    }

    /**
     * Adds a customer to the database.
     *
     * @param customer the customer to be added
     * @return true if the customer is successfully added, false otherwise
     */
    private boolean addCustomerToDatabase(Customer customer) {
        return bankerModel.addCustomer(customer);
    }

    /**
     * Adds an account to the database.
     *
     * @param account the account to be added
     * @return true if the account was successfully added, false otherwise
     */
    private boolean addAccountToDatabase(Account account) {
        return bankerModel.addAccount(account);
    }

    /**
     * Displays a success message after adding a customer and an account.
     *
     * @param customer      the customer object containing the customer details
     * @param accountNumber the account number of the added account
     */
    private void displaySuccessMessage(Customer customer, int accountNumber) {
        System.out.println(
                "\n" +
                        "***************************************************\n" +
                        "********************* AJOUT ***********************\n" +
                        "***************************************************\n");

        System.out.println(customer.getGender() + " " + customer.getName() + " " + customer.getFirstname()
                + " a été ajouté(e) avec succes.");
        System.out.println("Le compte n° " + accountNumber + " a été ajouté avec succes.\n");
        System.out.println(
                "***************************************************\n" +
                        "***************************************************\n");
    }

    /**
     * Returns a formatted string representation of the object.
     *
     * @return a string representation of the object
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
                "Position: " + this.getPosition() + "\n" +
                "*********************************************\n" +
                "*********************************************\n";
    }
}
