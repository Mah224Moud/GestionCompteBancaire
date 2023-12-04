package com.gestion.compte.bancaire;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB {
    private static final String url = "jdbc:mysql://localhost:3306/Test";
    private static final String username = "root";
    private static final String password = "Moud1997@";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);

            String query = "SELECT * FROM Essai";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        String firstname = resultSet.getString("firstname");
                        String creationDate = resultSet.getString("creation_date");

                        System.out.println("ID: " + id + ", Name: " + name + ", Firstname: " + firstname
                                + ", Creation Date: " + creationDate);

                    }
                }
            }
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
