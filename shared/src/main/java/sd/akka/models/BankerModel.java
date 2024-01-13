package sd.akka.models;

import sd.akka.banker.Banker;
import sd.akka.customer.Customer;
import sd.akka.database.DatabaseManager;
import sd.akka.account.Account;
import sd.akka.utils.History;
import sd.akka.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BankerModel {
    private DatabaseManager databaseManager;

    public BankerModel(final DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    /**
     * Retrieves a banker from the database based on the provided banker ID.
     *
     * @param bankerId The ID of the banker to retrieve.
     * @return The banker object if found, null otherwise.
     */
    public Banker getBankerById(int bankerId) {
        Banker banker = null;
        String query = "SELECT * FROM banker WHERE id = ?";
        try (Connection connection = databaseManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, bankerId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    banker = new Banker(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("firstname"),
                            resultSet.getString("gender"),
                            resultSet.getString("address"),
                            resultSet.getString("phone"),
                            resultSet.getString("position"),
                            resultSet.getString("email"));
                }
                databaseManager.closeConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return banker;
    }

    /**
     * Retrieves a Banker object based on the given email and password.
     *
     * @param email    the email of the banker
     * @param password the password of the banker
     * @return the Banker object corresponding to the email and password,
     *         or null if no matching banker is found
     */
    public Banker getBanker(String email, String password) {
        Banker banker = null;
        String query = "SELECT * FROM banker WHERE email = ?";

        try (Connection connection = databaseManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String hashedPassword = resultSet.getString("password");
                    if (Utils.isValidPassword(password, hashedPassword)) {
                        banker = new Banker(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("firstname"),
                                resultSet.getString("gender"),
                                resultSet.getString("address"),
                                resultSet.getString("phone"),
                                resultSet.getString("position"),
                                email);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        CommonModel commonModel = new CommonModel(databaseManager);
        if (banker == null || !commonModel.isLogged(email, password, "banker")) {
            throw new IllegalArgumentException("Adresse email ou mot de passe invalide !!!");
        }
        databaseManager.closeConnection();
        return banker;
    }

    /**
     * Adds a customer to the database.
     *
     * @param customer the customer object to be added
     * @return true if the customer was successfully added, false otherwise
     */
    public boolean addCustomer(Customer customer) {
        boolean success = false;
        String query = "INSERT INTO customer (id, name, firstname, gender, address, phone, type, account_number, banker_id, email, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = databaseManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            if (!Utils.isValidEmail(customer.getEmail())) {
                throw new IllegalArgumentException("L'adresse email est invalide.");
            }

            String hashedPassword = Utils.hashPassword(customer.getPassword());

            preparedStatement.setInt(1, customer.getId());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getFirstname());
            preparedStatement.setString(4, customer.getGender());
            preparedStatement.setString(5, customer.getAddress());
            preparedStatement.setString(6, customer.getPhone());
            preparedStatement.setString(7, customer.getType());
            preparedStatement.setInt(8, customer.getAccountNumber());
            preparedStatement.setInt(9, customer.getBankerId());
            preparedStatement.setString(10, customer.getEmail());
            preparedStatement.setString(11, hashedPassword);

            int rowsAffected = preparedStatement.executeUpdate();
            success = rowsAffected > 0;
            databaseManager.closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    /**
     * Adds an account to the database.
     *
     * @param account the Account object to be added
     * @return true if the account was successfully added, false otherwise
     */
    public boolean addAccount(Account account) {
        boolean success = false;
        String query = "INSERT INTO account (number, balance, status, customer_id, banker_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = databaseManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, account.getNumber());
            preparedStatement.setDouble(2, account.getBalance());
            preparedStatement.setString(3, account.getStatus());
            preparedStatement.setInt(4, account.getCustomerId());
            preparedStatement.setInt(5, account.getBankerId());

            int rowsAffected = preparedStatement.executeUpdate();
            success = rowsAffected > 0;
            databaseManager.closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    /**
     * Retrieves the ID of the last customer in the database.
     *
     * @return the ID of the last customer, or 1 if no customers exist
     */
    public int getLastCustomerId() {
        int lastCustomerId = 0;
        String query = "SELECT MAX(id) FROM customer";
        try (Connection connection = databaseManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    lastCustomerId = resultSet.getInt(1);
                }
            }
            databaseManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lastCustomerId > 0 ? lastCustomerId : 1;
    }

    /**
     * Retrieves the last account number from the database.
     *
     * @return the last account number, or 1 if no account number is found
     */
    public int getLastAccountNumber() {
        int lastAccountNumber = 0;
        String query = "SELECT number FROM account ORDER BY number DESC LIMIT 1";
        try (Connection connection = databaseManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    lastAccountNumber = resultSet.getInt("number");
                }
            }
            databaseManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lastAccountNumber > 0 ? lastAccountNumber : 1;
    }

    /**
     * Deposits a specified amount into a specified account.
     *
     * @param accountNumber the number of the account to deposit into
     * @param amount        the amount to deposit
     * @return true if the deposit was successful, false otherwise
     */
    public boolean deposit(int accountNumber, double amount) {
        boolean success = false;
        String query = "UPDATE account SET balance = balance + ? WHERE number = ?";

        try (Connection connection = databaseManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setDouble(1, amount);
            preparedStatement.setInt(2, accountNumber);

            int rowsAffected = preparedStatement.executeUpdate();
            success = rowsAffected > 0;
            databaseManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    /**
     * Withdraws a specified amount from a bank account.
     *
     * @param accountNumber the account number of the bank account
     * @param amount        the amount to be withdrawn
     * @return true if the withdrawal was successful, false otherwise
     */
    public boolean withdraw(int accountNumber, double amount) {
        boolean success = false;
        String query = "UPDATE account SET balance = balance - ? WHERE number = ?";

        try (Connection connection = databaseManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setDouble(1, amount);
            preparedStatement.setInt(2, accountNumber);

            int rowsAffected = preparedStatement.executeUpdate();
            success = rowsAffected > 0;
            databaseManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    /**
     * Adds a history record to the database.
     *
     * @param history the history object to be added
     * @return true if the history record was successfully added, false otherwise
     */
    public boolean addHistory(History history) {
        boolean success = false;
        String query = "INSERT INTO history (amount, type, account_number) VALUES (?, ?, ?)";

        try (Connection connection = databaseManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setDouble(1, history.getAmount());
            preparedStatement.setString(2, history.getType());
            preparedStatement.setInt(3, history.getAccoutNumber());

            int rowsAffected = preparedStatement.executeUpdate();
            success = rowsAffected > 0;
            databaseManager.closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    /**
     * Retrieves a list of History objects for a given account number.
     *
     * @param accountNumber the account number for which to retrieve histories
     * @return a list of History objects representing the account's transaction
     *         history
     */
    public List<History> getHistories(int accountNumber) {
        List<History> histories = new ArrayList<>();
        String query = "SELECT * FROM history WHERE account_number = ?";
        try (Connection connection = databaseManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, accountNumber);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    histories.add(new History(
                            resultSet.getInt("id"),
                            resultSet.getInt("account_number"),
                            resultSet.getDouble("amount"),
                            resultSet.getString("type"),
                            Utils.convertDate(resultSet.getString("date"))));
                }
                databaseManager.closeConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return histories;
    }
}
