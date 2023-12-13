package gestion.compte.bancaire.models;

import gestion.compte.bancaire.database.DatabaseManager;
import gestion.compte.bancaire.utils.Utils;

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
     * Obtient le solde d'un compte spécifié.
     *
     * @param accountNumber Le numéro du compte.
     * @return Le solde du compte.
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
     * Vérifie si un utilisateur est connecté.
     *
     * @param email    l'email de l'utilisateur
     * @param password le mot de passe de l'utilisateur
     * @param type     le type d'utilisateur (banquier ou client)
     * @return true si l'utilisateur est connecté, false sinon
     */
    public boolean isLogged(String email, String password, String type) {
        boolean isLoggedIn = false;
        String query = "";

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

}
