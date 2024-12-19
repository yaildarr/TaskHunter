package itis.inf304.taskhunter.servlets;

import itis.inf304.taskhunter.service.JobService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteJob")
public class DeleteJobServlet extends HttpServlet {

    JobService jobService;

    @Override
    public void init() throws ServletException {
        jobService = (JobService) getServletContext().getAttribute("jobService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jobId = req.getParameter("jobId");

        // Логика удаления объявления с указанным jobId
        boolean isDeleted = false; // Реализуйте эту функцию для удаления из базы данных
        try {
            isDeleted = jobService.deleteJobById(Integer.parseInt(jobId));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        // Возвращаем ответ в формате JSON
        resp.setContentType("application/json");
        resp.getWriter().write("{\"success\": " + isDeleted + "}");
    }
}
