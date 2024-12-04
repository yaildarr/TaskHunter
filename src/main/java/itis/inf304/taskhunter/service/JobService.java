package itis.inf304.taskhunter.service;

import itis.inf304.taskhunter.dao.JobDao;
import itis.inf304.taskhunter.entities.Job;
import itis.inf304.taskhunter.servlets.JobDetailServlet;

import java.sql.SQLException;
import java.util.logging.Logger;

public class JobService {

    JobDao jobDao;

    private static final Logger LOG = Logger.getLogger(JobService.class.getName());

    public JobService(JobDao jobDao) {
        this.jobDao = jobDao;
    }

    public Job getJobById(int id) throws SQLException {
        Job job = jobDao.getJobById(id);
        LOG.info(job.toString());
        return job;
    }
}
