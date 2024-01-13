package sd.akka.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String url = "jdbc:mysql://localhost:3306/GestionCompteBancaire";
    private static final String username = "root";
    private static final String password = "Moud1997@";
    private static Connection connection = null;

    /**
     * Retrieves the connection to the database. If the connection is null or
     * closed,
     * a new connection is established using the provided URL, username, and
     * password.
     *
     * @return the connection to the database
     */
    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(url, username, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Closes the connection to the database and performs necessary cleanup.
     *
     * @param None
     * @return None
     */
    public void closeConnection() {
        try {
            connection.close();
            com.mysql.cj.jdbc.AbandonedConnectionCleanupThread.checkedShutdown();
        } catch (

        Exception e) {
            e.printStackTrace();
        }
    }
}