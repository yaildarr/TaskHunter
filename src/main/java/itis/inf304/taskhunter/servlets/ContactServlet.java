package itis.inf304.taskhunter.servlets;

import itis.inf304.taskhunter.dao.JobCategoryDao;
import itis.inf304.taskhunter.entities.Job;
import itis.inf304.taskhunter.entities.JobCategory;
import itis.inf304.taskhunter.service.JobCategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/contact")
public class ContactServlet extends HttpServlet {

    JobCategoryService jobCategoryService;

    @Override
    public void init() throws ServletException {
        jobCategoryService = (JobCategoryService) getServletContext().getAttribute("jobCategoryService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("title","Контакты");
        try {
            List<JobCategory> jobCategories = jobCategoryService.getAllJobCategories();
            req.setAttribute("jobCategories",jobCategories);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/view/contact.jsp").forward(req, resp);}
}

