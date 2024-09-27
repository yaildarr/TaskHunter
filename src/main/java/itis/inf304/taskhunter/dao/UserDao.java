package itis.inf304.taskhunter.dao;

import itis.inf304.taskhunter.entities.User;
import itis.inf304.taskhunter.util.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends AbstractController{


    public UserDao(ConnectionProvider connectionProvider) {
        super(connectionProvider);
    }

    public User getUserByEmail(String email) throws SQLException {
        try {
            PreparedStatement ps = (getPrepareStatement("SELECT * FROM user WHERE email = ?"));
            ResultSet rs = ps.executeQuery();
            boolean found = rs.next();
            if (found) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("username"),
                        rs.getString("number")
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new SQLException("Can't get user from db.", e);
        }
    }
}
