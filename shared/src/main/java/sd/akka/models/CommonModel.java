package sd.akka.models;

import sd.akka.database.DatabaseManager;
import sd.akka.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommonModel {

    private DatabaseManager databaseManager;

    public CommonModel(final DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    /**
     * Retrieves the balance for the specified account number.
     *
     * @param accountNumber the account number to retrieve the balance for
     * @return the formatted balance amount as a string
     */
    public String getBalance(int accountNumber) {
        double balance = 0;
        String query = "SELECT balance FROM account WHERE number = ?";
        try (
                Connection connection = databaseManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, accountNumber);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    balance = resultSet.getDouble("balance");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Utils.formatAmount(balance);
    }

    /**
     * Determines whether a user is logged in or not.
     *
     * @param email    the email of the user
     * @param password the password of the user
     * @param type     the type of user ("banker" or "customer")
     * @return true if the user is logged in, false otherwise
     */
    public boolean isLogged(String email, String password, String type) {
        boolean isLoggedIn = false;
        String query = "";

        if (!Utils.isValidEmail(email)) {
            throw new IllegalArgumentException(
                    "L'email est invalide !!! Pensez à entrer une adresse email valide !!!");
        }

        if (type.equals("banker")) {
            query = "SELECT password FROM banker WHERE email = ?";
        } else if (type.equals("customer")) {
            query = "SELECT password FROM customer WHERE email = ?";
        } else {
            throw new IllegalArgumentException(
                    "Le type de l'utisateur est invalide il doit etre soit banquier ou client");
        }

        try (
                Connection connection = databaseManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String hashedPassword = resultSet.getString("password");
                    isLoggedIn = Utils.isValidPassword(password, hashedPassword);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isLoggedIn;
    }

    /**
     * Determines whether an account with the given account number exists.
     *
     * @param accountNumber the account number to check
     * @return true if an account with the given number exists, false otherwise
     */
    public boolean accountExists(int accountNumber) {
        boolean exists = false;
        String query = "SELECT 1 FROM account WHERE number = ?";
        try (
                Connection connection = databaseManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, accountNumber);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    exists = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

}
