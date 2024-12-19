package itis.inf304.taskhunter.servlets;

import com.google.gson.Gson;
import itis.inf304.taskhunter.dao.JobCategoryDao;
import itis.inf304.taskhunter.dao.JobDao;
import itis.inf304.taskhunter.entities.Job;
import itis.inf304.taskhunter.entities.JobCategory;
import itis.inf304.taskhunter.service.JobCategoryService;
import itis.inf304.taskhunter.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/jobs")
public class HomeServlet extends HttpServlet {

    JobCategoryDao jobCategoryDao;
    private JobDao jobDao;
    private LoginService loginService;
    JobCategoryService jobCategoryService;
    private static final Logger LOG = Logger.getLogger(HomeServlet.class.getName());


    @Override
    public void init() throws ServletException {
        jobDao = (JobDao) getServletContext().getAttribute("jobDao");
        jobCategoryService = (JobCategoryService) getServletContext().getAttribute("jobCategoryService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        try {
            List<JobCategory> jobCategories = jobCategoryService.getAllJobCategories();
            req.setAttribute("jobCategories",jobCategories);
            Cookie[] cookie = req.getCookies();
            if (cookie != null) {
                for (Cookie c : cookie) {
                    String cookieName = c.getValue();
                    if (c.getName().equals("city")) {
                        session.setAttribute("city", cookieName);
                    }
                }
            }
            req.setAttribute("title","Главная");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/view/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showJobs(req, resp);
    }


    public void showJobs(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int offset = 0;
        int limit = 10;

        // Параметры для пагинации
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

        // Параметры фильтров
        String search = req.getParameter("search");
        String category = req.getParameter("category");
        LOG.info(category + " category");
        String minPaymentParam = req.getParameter("minPayment");
        LOG.info(minPaymentParam + " minPayment");
        String maxPaymentParam = req.getParameter("maxPayment");
        String city = req.getParameter("city");
        LOG.info(city + " city");

        Double minPayment = null;
        Double maxPayment = null;

        // Преобразование оплаты в Integer
        try {
            if (minPaymentParam != null && !minPaymentParam.isEmpty()) {
                minPayment = Double.parseDouble(minPaymentParam);
            }
            if (maxPaymentParam != null && !maxPaymentParam.isEmpty()) {
                maxPayment = Double.parseDouble(maxPaymentParam);
            }
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid payment value");
            return;
        }

        // Получение вакансий с фильтрами

        List<Job> jobs = jobDao.getJobs(city, search, category, minPayment, maxPayment, offset, limit);

        Cookie cookie = new Cookie("city", city+"");
        cookie.setMaxAge(24*60*60*3);
        cookie.setPath("/");
        resp.addCookie(cookie);

        // Формирование JSON ответа
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        Gson gson = new Gson();
        String jsonJobs = gson.toJson(jobs);
        try (PrintWriter writer = resp.getWriter()) {
            writer.write(jsonJobs);
        }
    }
}
