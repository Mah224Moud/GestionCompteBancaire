package com.gestion.compte.bancaire.models;

import com.gestion.compte.bancaire.banker.Banker;
import com.gestion.compte.bancaire.customer.Customer;
import com.gestion.compte.bancaire.database.DatabaseManager;
import com.gestion.compte.bancaire.utils.Utils;
import com.gestion.compte.bancaire.accounts.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankerModel {
    private DatabaseManager databaseManager;

    public BankerModel() {
        databaseManager = new DatabaseManager();
    }

    /**
     * Obtient un objet Banker à partir de son ID.
     *
     * @param bankerId L'ID du banquier.
     * @return Un objet Banker ou null si non trouvé.
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return banker;
    }

    /**
     * Obtient un objet Banker à partir de son email et mot de passe.
     *
     * @param email    L'email du banquier.
     * @param password Le mot de passe du banquier.
     * @return Un objet Banker ou null si non trouvé.
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
        CommonModel commonModel = new CommonModel();
        if (banker == null || !commonModel.isLogged(email, password, "banker")) {
            throw new IllegalArgumentException("Adresse email ou mot de passe invalide !!!");
        }
        return banker;
    }

    /**
     * Ajoute un client à la base de données.
     *
     * @param bankerId L'ID du banquier.
     * @param customer L'objet Customer à ajouter.
     * @return true si l'ajout a réussi, false sinon.
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

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    /**
     * Obtient l'ID du dernier client dans la base de données.
     *
     * @param bankerId   L'ID du banquier.
     * @param customerId L'ID du client.
     * @param Account    L'objet Account à ajouter.
     * 
     * @return L'ID du dernier client.
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

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    /**
     * Obtient l'ID du dernier client dans la base de données.
     * 
     * @return L'ID du dernier client.
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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lastCustomerId;
    }

    /**
     * Obtient le numero de compte du dernier compte dans la base de données.
     * 
     * @return Le numero de compte du dernier compte.
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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lastAccountNumber;
    }
}
