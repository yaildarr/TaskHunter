package itis.inf304.taskhunter.dao;

import itis.inf304.taskhunter.entities.JobCategory;
import itis.inf304.taskhunter.entities.User;
import itis.inf304.taskhunter.util.ConnectionProvider;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobCategoryDao extends AbstractController {

    public JobCategoryDao(ConnectionProvider connectionProvider) {
        super(connectionProvider);
    }

    public List<JobCategory> getJobCategories() throws SQLException {
        List<JobCategory> jobCategories = new ArrayList<>();
        String sql = "SELECT * FROM category";
        try (PreparedStatement ps = getPrepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    JobCategory jobCategory = new JobCategory(
                            rs.getInt("id"),
                            rs.getString("name")
                    );
                    jobCategories.add(jobCategory);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return jobCategories;
    }
}
