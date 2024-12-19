package itis.inf304.taskhunter.service;

import itis.inf304.taskhunter.dao.JobDao;
import itis.inf304.taskhunter.entities.Job;
import itis.inf304.taskhunter.servlets.JobDetailServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
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
    public List<Job> getJobsById(int userId,int offset, int limit) throws SQLException {
        List<Job> jobs = jobDao.getJobsByUserId(userId,offset,limit);
        return jobs;
    }
    public List<Job> getJobsById(int userId) throws SQLException {
        return jobDao.getJobsByUserId(userId);
    }

    public boolean deleteJobById(int id) throws SQLException {
        return jobDao.deleteJobById(id);
    }
 }
