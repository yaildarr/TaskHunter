package itis.inf304.taskhunter.service;

import itis.inf304.taskhunter.dao.JobCategoryDao;
import itis.inf304.taskhunter.entities.JobCategory;

import java.sql.SQLException;
import java.util.List;

public class JobCategoryService {
    JobCategoryDao jobCategoryDao;

    public JobCategoryService(JobCategoryDao jobCategoryDao) {
        this.jobCategoryDao = jobCategoryDao;
    }

    public List<JobCategory> getAllJobCategories() throws SQLException {
        return jobCategoryDao.getJobCategories();
    }
}
