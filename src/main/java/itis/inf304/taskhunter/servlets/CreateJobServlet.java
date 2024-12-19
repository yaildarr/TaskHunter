package itis.inf304.taskhunter.servlets;

import itis.inf304.taskhunter.dao.JobCategoryDao;
import itis.inf304.taskhunter.entities.JobCategory;
import itis.inf304.taskhunter.service.CreateJobService;
import itis.inf304.taskhunter.service.SecurityService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;


@WebServlet("/createJob")
public class CreateJobServlet extends HttpServlet {
    JobCategoryDao jobCategoryDao;
    CreateJobService createJobService;

    private static final Logger LOG = Logger.getLogger(CreateJobServlet.class.getName());


    @Override
    public void init() throws ServletException {
        jobCategoryDao = (JobCategoryDao) getServletContext().getAttribute("jobCategoryDao");
        createJobService = (CreateJobService) getServletContext().getAttribute("createJobService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<JobCategory> jobCategories = jobCategoryDao.getJobCategories();
            req.setAttribute("jobCategories",jobCategories);
            req.setAttribute("title","Разместить объявление");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/view/createJob.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("doPost");
        try {
            createJobService.createJob(req);
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось вызвать createJobService.createJob()");
        }
        resp.sendRedirect(getServletContext().getContextPath() + "/jobs");
    }
}
