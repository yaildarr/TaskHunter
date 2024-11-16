package itis.inf304.taskhunter.servlets;

import com.google.gson.Gson;
import itis.inf304.taskhunter.dao.JobDao;
import itis.inf304.taskhunter.entities.Job;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/api/jobs")
public class HomeServlet extends HttpServlet {

    private JobDao jobDao;

    @Override
    public void init() throws ServletException {
        jobDao = (JobDao) getServletContext().getAttribute("jobDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/view/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int offset = 0;
        int limit = 10;

        String offsetParam = req.getParameter("offset");
        String limitParam = req.getParameter("limit");

        if (offsetParam != null) {
            try {
                offset = Integer.parseInt(offsetParam);
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid offset value");
                return;
            }
        }

        if (limitParam != null) {
            try {
                limit = Integer.parseInt(limitParam);
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid limit value");
                return;
            }
        }

        List<Job> jobs = jobDao.getJobs(offset,limit);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        Gson gson = new Gson();
        String jsonJobs = gson.toJson(jobs);
        resp.getWriter().write(jsonJobs);
    }
}
