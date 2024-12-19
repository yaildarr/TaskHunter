package itis.inf304.taskhunter.dao;

import itis.inf304.taskhunter.dto.CreateJobDto;
import itis.inf304.taskhunter.entities.Job;
import itis.inf304.taskhunter.servlets.JobDetailServlet;
import itis.inf304.taskhunter.util.ConnectionProvider;
import itis.inf304.taskhunter.util.DateFormatter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Logger;

public class JobDao extends AbstractController{

    public JobDao(ConnectionProvider connectionProvider) {
        super(connectionProvider);
    }

    private static final Logger LOG = Logger.getLogger(JobDao.class.getName());

    public List<Job> getJobs(String city, String search, String category, Double minPayment, Double maxPayment, int offset, int limit) {
        StringBuilder sql = new StringBuilder(
                "SELECT id, user_id, title, location, description, payment, category_id, created_at " +
                        "FROM job WHERE is_active = true"
        );

        LOG.info(search + " search");
        List<Object> params = new ArrayList<>();

        if (city != null && !city.trim().isEmpty()) {
            sql.append(" AND location LIKE LOWER(?)");
            params.add("%" + city.trim() + "%");
        }

        // Динамическое добавление условий
        if (search != null && !search.trim().isEmpty()) {
            sql.append(" AND (title LIKE LOWER(?) OR description LIKE LOWER(?))");
            params.add("%" + search.trim() + "%");
            params.add("%" + search.trim() + "%");
        }

        if (category != null && !category.trim().isEmpty()) {
            if (Integer.parseInt(category) != 0){
                sql.append(" AND category_id = ?");
                params.add(Integer.parseInt(category)); // category_id предполагается числовым
            }
        }

        if (minPayment != null) {
            sql.append(" AND payment >= ?");
            params.add(minPayment);
        }

        if (maxPayment != null) {
            sql.append(" AND payment <= ?");
            params.add(maxPayment);
        }

        sql.append(" ORDER BY created_at DESC LIMIT ? OFFSET ?");
        params.add(limit);
        params.add(offset);
        for (int i = 0; i < params.size(); i++) {
            params.get(i).toString();
        }
        List<Job> jobs = new ArrayList<>();

        try (PreparedStatement ps = getPrepareStatement(sql.toString())) {
            // Установка параметров в PreparedStatement
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

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
                            DateFormatter.formatDateTime(rs.getTimestamp("created_at").toLocalDateTime())
                    );
                    jobs.add(job);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось получить данные", e);
        }

        return jobs;
    }

    public Job getJobById(int id) throws SQLException {
        String sql = "SELECT * FROM job WHERE id = ?";
        try (PreparedStatement ps = getPrepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Job(
                            rs.getInt("id"),
                            rs.getInt("user_id"),
                            rs.getString("title"),
                            rs.getString("location"),
                            rs.getString("description"),
                            rs.getDouble("payment"),
                            rs.getInt("category_id"),
                            DateFormatter.formatDateTime(rs.getTimestamp("created_at").toLocalDateTime())
                    );
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
        throw new SQLException("Не удалось получить объявление из базы данных.", e);
        }
    }

    public Boolean createJob(CreateJobDto job) throws SQLException {
        String sql = "INSERT INTO job (user_id, title, location, description, payment, category_id, is_active) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = getPrepareStatement(sql)) {
            ps.setInt(1, job.getUserId());
            ps.setString(2, job.getTitle());
            ps.setString(3, job.getLocation());
            ps.setString(4, job.getDescription());
            ps.setDouble(5, job.getPayment());
            ps.setInt(6, job.getCategoryId());
            ps.setInt(7, Integer.parseInt("1"));
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    public List<Job> getJobsByUserId(int userId, int offset, int limit) throws SQLException {
        String sql = "SELECT * FROM job WHERE user_id = ? WHERE is_active = true LIMIT ? OFFSET ?";
        List<Job> jobs = new ArrayList<>();
        try (PreparedStatement ps = getPrepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, limit);
            ps.setInt(3, offset);
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
                            DateFormatter.formatDateTime(rs.getTimestamp("created_at").toLocalDateTime())
                    );
                    jobs.add(job);
                }
            }
        }
        return jobs;
    }

    public List<Job> getJobsByUserId(int userId) throws SQLException {
        String sql = "SELECT * FROM job WHERE user_id = ? AND is_active = true";
        List<Job> jobs = new ArrayList<>();
        try (PreparedStatement ps = getPrepareStatement(sql)) {
            ps.setInt(1, userId);
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
                            DateFormatter.formatDateTime(rs.getTimestamp("created_at").toLocalDateTime())
                    );
                    jobs.add(job);
                }
            } catch (SQLException e){
                System.err.println(e.getMessage());
            }
        }
        return jobs;
    }
    public boolean deleteAllJobsByUserId(int userId) throws SQLException {
        String sql = "DELETE FROM job WHERE user_id = ?";
        try (PreparedStatement ps = getPrepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
    public boolean deleteJobById(int id) throws SQLException {
        String sql = "DELETE FROM job WHERE id = ?";
        try (PreparedStatement ps = getPrepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
}
