package gestion.compte.bancaire.banker;

import gestion.compte.bancaire.models.CommonModel;
import gestion.compte.bancaire.utils.User;
import gestion.compte.bancaire.utils.Utils;
import gestion.compte.bancaire.customer.Customer;
import gestion.compte.bancaire.database.DatabaseManager;
import gestion.compte.bancaire.models.BankerModel;
import gestion.compte.bancaire.account.Account;
import gestion.compte.bancaire.utils.Action;

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

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public void seeBalance(int accountNumber) {
        CommonModel commonModel = new CommonModel(databaseManager);
        System.out.println("Le solde du compte n°" + accountNumber + " est de "
                + commonModel.getBalance(accountNumber)
                + "€");
    }

    @Override
    public String withdraw(double amount, int accountNumber) throws Exception {
        if (!bankerModel.withdraw(accountNumber, amount)) {
            throw new Exception("Retrait Impossible.\n");
        }

        return "\nRetrait de " + Utils.formatAmount(amount) + "€ effectué avec succès.\n";
    }

    @Override
    public String deposit(double amount, int accountNumber) throws Exception {
        if (!bankerModel.deposit(accountNumber, amount)) {
            throw new Exception("Depot Impossible.\n");
        }

        return "\nDepot de " + Utils.formatAmount(amount) + "€ effectfué avec succès.\n";
    }

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

    private int generateAccountNumber() {
        return bankerModel.getLastAccountNumber() + 1;
    }

    private int generateCustomerId() {
        return bankerModel.getLastCustomerId() + 1;
    }

    private boolean addCustomerToDatabase(Customer customer) {
        return bankerModel.addCustomer(customer);
    }

    private boolean addAccountToDatabase(Account account) {
        return bankerModel.addAccount(account);
    }

    private void displaySuccessMessage(Customer customer, int accountNumber) {
        System.out.println(
                "\n" +
                        "***************************************************\n" +
                        "********************* AJOUT ***********************\n" +
                        "***************************************************\n");

        System.out.println(customer.toString() + " a été ajouté(e) avec succes.");
        System.out.println("Le compte n° " + accountNumber + " a été ajouté avec succes.\n");
    }

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