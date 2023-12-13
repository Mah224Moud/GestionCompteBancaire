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
