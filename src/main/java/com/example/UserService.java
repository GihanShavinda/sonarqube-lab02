package main.java.com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class UserService {

    private static final Logger logger = Logger.getLogger(UserService.class.getName());

    private final String dbUrl    = System.getenv("DB_URL");
    private final String dbUser   = System.getenv("DB_USER");
    private final String dbPassword = System.getenv("DB_PASSWORD");

    public boolean findUser(String username) throws SQLException {
        String query = "SELECT id, name, email FROM users WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public void deleteUser(String username) throws SQLException {
        String query = "DELETE FROM users WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, username);
            logger.info("Deleting user: {0}");
            ps.execute();
        }
    }
}