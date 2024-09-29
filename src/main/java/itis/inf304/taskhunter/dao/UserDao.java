package itis.inf304.taskhunter.dao;

import itis.inf304.taskhunter.entities.User;
import itis.inf304.taskhunter.util.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends AbstractController {

    public UserDao(ConnectionProvider connectionProvider) {
        super(connectionProvider);
    }

    public boolean newUser(User user) {
        String sql = "INSERT INTO user (username, email, password_hash, phone) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = getPrepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getNumber().toString());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Ошибка при добавлении пользователя: " + e.getMessage());
            return false;
        }
    }

    public User getUserByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM user WHERE email = ?";
        try (PreparedStatement ps = getPrepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("id"),
                            rs.getString("email"),
                            rs.getString("password_hash"),
                            rs.getString("username"),
                            rs.getString("phone")
                    );
                } else {
                    throw new SQLException("User not found");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Can't get user from db.", e);
        }
    }
}
