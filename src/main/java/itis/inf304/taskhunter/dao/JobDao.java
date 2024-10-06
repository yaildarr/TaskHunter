package itis.inf304.taskhunter.dao;

import itis.inf304.taskhunter.entities.Job;
import itis.inf304.taskhunter.util.ConnectionProvider;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobDao extends AbstractController{

    public JobDao(ConnectionProvider connectionProvider) {
        super(connectionProvider);
    }


    public List<Job> getJobs(int offset, int limit) {
        String sql = "SELECT id, user_id, title, location, description, payment, category_id, created_at FROM job WHERE is_active = true LIMIT ? OFFSET ?";

        List<Job> jobs = new ArrayList<>();

        try (PreparedStatement ps = getPrepareStatement(sql)) {
            ps.setInt(1, limit);
            ps.setInt(2, offset);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Job job = new Job(
                            rs.getInt("id"),
                            rs.getInt("user_id"),
                            rs.getString("title"),
                            rs.getString("location"),
                            rs.getString("description"),
                            rs.getDouble("payment"),
                            rs.getInt("category_id"),
                            rs.getTimestamp("created_at").toLocalDateTime()
                    );
                    jobs.add(job);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось получить данные", e);
        }

        return jobs;
    }
}
