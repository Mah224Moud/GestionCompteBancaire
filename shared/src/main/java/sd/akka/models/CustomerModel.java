package sd.akka.models;

import sd.akka.customer.Customer;
import sd.akka.database.DatabaseManager;
import sd.akka.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerModel {

    private DatabaseManager databaseManager;

    public CustomerModel(final DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    /**
     * Retrieves a customer from the database using the provided email and password.
     *
     * @param email    the email of the customer
     * @param password the password of the customer
     * @return the Customer object if the email and password match, null otherwise
     */
    public Customer getCustomer(String email, String password) {
        Customer customer = null;
        String query = "SELECT * FROM customer WHERE email = ?";

        try (Connection connection = databaseManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String hashedPassword = resultSet.getString("password");
                    if (Utils.isValidPassword(password, hashedPassword)) {
                        customer = new Customer(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("firstname"),
                                resultSet.getString("gender"),
                                resultSet.getString("address"),
                                resultSet.getString("phone"),
                                resultSet.getString("type"),
                                resultSet.getInt("account_number"),
                                resultSet.getInt("banker_id"),
                                resultSet.getString("email"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        CommonModel commonModel = new CommonModel(databaseManager);
        if (customer == null || !commonModel.isLogged(email, password, "customer")) {
            throw new IllegalArgumentException("Adresse email ou mot de passe invalide !!!");
        }
        databaseManager.closeConnection();
        return customer;
    }

    /**
     * Retrieves a customer from the database by their ID.
     *
     * @param customerId the ID of the customer to retrieve
     * @return the Customer object representing the customer with the given ID,
     *         or null if no customer with the given ID exists
     */
    public Customer getCustomerById(int customerId) {
        Customer customer = null;
        String query = "SELECT * FROM customer WHERE id = ?";

        try (Connection connection = databaseManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, customerId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    customer = new Customer(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("firstname"),
                            resultSet.getString("gender"),
                            resultSet.getString("address"),
                            resultSet.getString("phone"),
                            resultSet.getString("type"),
                            resultSet.getInt("account_number"),
                            resultSet.getInt("banker_id"),
                            resultSet.getString("email"));
                }
            }
            databaseManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer;
    }
}
