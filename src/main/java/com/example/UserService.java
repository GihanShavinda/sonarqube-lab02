package main.java.com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

public class UserService {

    private static final Logger logger = Logger.getLogger(UserService.class.getName());

    private final String dbUrl    = System.getenv("DB_URL");
    private final String dbUser   = System.getenv("DB_USER");
    private final String dbPassword = System.getenv("DB_PASSWORD");

    public void findUser(String username) throws SQLException {
        String query = "SELECT * FROM users WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, username);
            ps.executeQuery();
        }
    }

    public void deleteUser(String username) throws SQLException {
        String query = "DELETE FROM users WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, username);
            logger.info("Deleting user: " + username);
            ps.execute();
        }
    }
}