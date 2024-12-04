package itis.inf304.taskhunter.servlets;

import itis.inf304.taskhunter.entities.Job;
import itis.inf304.taskhunter.entities.JobCategory;
import itis.inf304.taskhunter.service.JobService;
import itis.inf304.taskhunter.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

@WebServlet("/jobs/job")
public class JobDetailServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(JobDetailServlet.class.getName());

    JobService jobService;
    Job jobFromDb;
    UserService userService;


    @Override
    public void init() throws ServletException {
        jobService = (JobService) getServletContext().getAttribute("jobService");
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jobId = req.getParameter("id");
        try {
            jobFromDb = jobService.getJobById(Integer.parseInt(jobId));
            req.setAttribute("UserForJobDto",userService.getUserForJobById(jobFromDb.getUserId()));
            req.setAttribute("job", jobFromDb);
            req.setAttribute("jobCategory" , new JobCategory(jobFromDb.getCategoryId()));
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось получить данные об объявлении");
        }
        getServletContext().getRequestDispatcher("/WEB-INF/view/jobDetail.jsp").forward(req, resp);
    }
}
