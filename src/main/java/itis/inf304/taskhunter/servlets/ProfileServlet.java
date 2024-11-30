package itis.inf304.taskhunter.servlets;

import itis.inf304.taskhunter.dao.UserDao;
import itis.inf304.taskhunter.entities.User;
import itis.inf304.taskhunter.service.SecurityService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;


@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(ProfileServlet.class.getName());


    SecurityService securityService;
    UserDao userDao;
    User user;
    HttpSession session;

    @Override
    public void init() throws ServletException {
        securityService = (SecurityService) getServletContext().getAttribute("securityService");
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/view/profile.jsp").forward(req, resp);
        session = req.getSession();
        user = (User) session.getAttribute("user");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String number = req.getParameter("number");
        String description = req.getParameter("description");
        String photoUrl = req.getParameter("photoUrl");
        LOG.info(username + " " + email + " " + number + " " + description + " " + photoUrl);
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
        try {
            LOG.info(user.getEmail() + " " + user.getUsername() + " " + user.getNumber() + " " + user.getDescription() + " " + user.getId());
            securityService.updateUser(user);
            session.setAttribute("user", user);
            resp.sendRedirect(getServletContext().getContextPath() + "/profile");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
