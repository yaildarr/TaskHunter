package itis.inf304.taskhunter.dao;

import itis.inf304.taskhunter.util.ConnectionProvider;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RespondDao extends AbstractController {
    public RespondDao(ConnectionProvider connectionProvider) {
        super(connectionProvider);
    }

    public boolean postRespond(int userId, int jobId) throws SQLException {
        String sql = "INSERT INTO responses (user_id, job_id) VALUES (?, ?)";
        try (PreparedStatement ps = getPrepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, jobId);
            ps.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public int getRespondCountById(int jobId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM responses WHERE job_id = ?";
        try (PreparedStatement ps = getPrepareStatement(sql)) {
            ps.setInt(1, jobId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
                return 0;
            }
        }
    }
}
