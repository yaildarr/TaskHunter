package itis.inf304.taskhunter.dao;

import itis.inf304.taskhunter.entities.Message;
import itis.inf304.taskhunter.util.ConnectionProvider;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MessageDao extends AbstractController{
    public MessageDao(ConnectionProvider connectionProvider) {
        super(connectionProvider);
    }

    public boolean sendMessage(Message message) throws SQLException {
        String sql = "INSERT INTO messages (name, email, sender_id, message_text) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = getPrepareStatement(sql)){
            ps.setString(1, message.getName());
            ps.setString(2, message.getEmail());
            ps.setString(3,message.getUserId().toString());
            ps.setString(4, message.getMessage());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
        System.err.println("Ошибка при создании сообщения " + e.getMessage());
        return false;
        }
    }
}
