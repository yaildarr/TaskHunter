package itis.inf304.taskhunter.servlets;

import itis.inf304.taskhunter.dao.UserDao;
import itis.inf304.taskhunter.dto.UserForJobDto;
import itis.inf304.taskhunter.entities.Job;
import itis.inf304.taskhunter.entities.User;
import itis.inf304.taskhunter.service.JobService;
import itis.inf304.taskhunter.service.SecurityService;
import itis.inf304.taskhunter.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(ProfileServlet.class.getName());


    SecurityService securityService;
    UserDao userDao;
    User user;
    HttpSession session;
    UserService userService;
    Integer userId;
    JobService jobService;

    @Override
    public void init() throws ServletException {
        securityService = (SecurityService) getServletContext().getAttribute("securityService");
        userService = (UserService) getServletContext().getAttribute("userService");
        jobService = (JobService) getServletContext().getAttribute("jobService");
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        getServletContext().getRequestDispatcher("/WEB-INF/view/profile.jsp").forward(req, resp);
//        session = req.getSession();
//        user = (User) session.getAttribute("user");
//        LOG.info(user.getDescription() + user.getUsername());
        String idParam = req.getParameter("id");
        if (idParam != null) {
            userId = Integer.parseInt(idParam);
        }
        user = (User) req.getSession().getAttribute("user");
        req.setAttribute("title",user.getUsername());
        LOG.info(user.getUsername());
        if (idParam == null && user == null) {
            resp.sendRedirect(req.getContextPath() + "/jobs");
        }
        if (user != null && userId != null) {
            if (userId != (user.getId())){
                try {
                    User userJob = userService.getUserById(userId);
                    if (userJob == null){
                        RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp");
                        dispatcher.forward(req, resp);
                    } else {
                        req.setAttribute("otherUser", userJob);
                        try {
                            List<Job> jobs = jobService.getJobsById(userId);
                            System.out.println(jobs.size());
                            req.setAttribute("jobs", jobs);
                        } catch (SQLException e) {
                            System.err.println("Не удалось получить объявления");
                        }
                        getServletContext().getRequestDispatcher("/WEB-INF/view/otherUserProfile.jsp").forward(req, resp);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
//                getServletContext().getRequestDispatcher("/WEB-INF/view/otherUserProfile.jsp").forward(req, resp);
            } else if (userId == user.getId()) {
                try {
                    List<Job> jobs = jobService.getJobsById(user.getId());
                    System.out.println(jobs.size());
                    req.setAttribute("jobs", jobs);
                } catch (SQLException e) {
                    System.err.println("Не удалось получить объявления");
                }
                getServletContext().getRequestDispatcher("/WEB-INF/view/profile.jsp").forward(req, resp);
            }
        } else if (userId == null && user != null) {
            try {
                List<Job> jobs = jobService.getJobsById(user.getId());
                System.out.println(jobs.size());
                req.setAttribute("jobs", jobs);
            } catch (SQLException e) {
                System.err.println("Не удалось получить объявления");
            }
            getServletContext().getRequestDispatcher("/WEB-INF/view/profile.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        session = req.getSession();
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String number = req.getParameter("number");
        String description = req.getParameter("description");
        String photoUrl = (String) req.getSession().getAttribute("newPhotoUrl");
        LOG.info(username+" -Имя");
        if (username != null && !username.isEmpty()) {
            user.setUsername(username);
        }
        if (email != null && !email.isEmpty()) {
            user.setEmail(email);
        }
        if (number != null && !number.isEmpty()) {
            user.setNumber(number);
        }
        if (description != null && !description.isEmpty()) {
            user.setDescription(description);
        }
        if (photoUrl != null && !photoUrl.isEmpty()) {
            user.setPhotoURL(photoUrl);
        }
        try {
            securityService.updateUser(user);
            session.removeAttribute("user");
            session.setAttribute("user", user);
            resp.sendRedirect(getServletContext().getContextPath() + "/profile");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
