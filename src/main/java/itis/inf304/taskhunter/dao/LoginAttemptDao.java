package itis.inf304.taskhunter.dao;


import itis.inf304.taskhunter.util.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoginAttemptDao extends AbstractController{


    public LoginAttemptDao(ConnectionProvider connectionProvider) {
        super(connectionProvider);
    }

    public void saveLoginAttempt(String username, Boolean status) {
        String sql = "INSERT INTO authorization_attempts (email, status) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = getPrepareStatement(sql)) {
            preparedStatement.setString(1, username);
            preparedStatement.setBoolean(2, status);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save login attempt", e);
        }
    }
}
