package itis.inf304.taskhunter.service;

import itis.inf304.taskhunter.dao.JobDao;
import itis.inf304.taskhunter.dto.CreateJobDto;
import itis.inf304.taskhunter.entities.Job;
import itis.inf304.taskhunter.entities.JobCategory;
import itis.inf304.taskhunter.entities.User;
import itis.inf304.taskhunter.servlets.ProfileServlet;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.logging.Logger;

public class CreateJobService {

    JobDao jobDao;

    private static final Logger LOG = Logger.getLogger(CreateJobService.class.getName());


    public CreateJobService(JobDao jobDao) {
        this.jobDao = jobDao;
    }

    public boolean createJob(HttpServletRequest req) throws SQLException {
        User user = (User) req.getSession().getAttribute("user");
        String title = req.getParameter("title");
        LOG.info(title);
        String description = req.getParameter("description");
        String location = req.getParameter("city");
        LOG.info(location + " LOCATION");
        String category = req.getParameter("category");
        String payment = req.getParameter("payment");
        LOG.info(category);
        try {
            jobDao.createJob(new CreateJobDto(
                    user.getId(),
                    title,
                    location,
                    description,
                    Double.parseDouble(payment),
                    Integer.parseInt(category),
                    null
            ));
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
