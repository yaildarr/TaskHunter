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


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    SecurityService securityService;
    UserDao userDao;
    User user;

    @Override
    public void init() throws ServletException {
        securityService = (SecurityService) getServletContext().getAttribute("securityService");
        userDao = (UserDao) getServletContext().getAttribute("userDao");
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        try {
            if (securityService.login(getServletContext(), new User(email, password))) {
                user = userDao.getUserByEmail(email);
                HttpSession session = req.getSession();
                session.setAttribute("username", user.getUsername());
                session.setAttribute("user", user);
                resp.sendRedirect(getServletContext().getContextPath() + "/api/jobs");
            } else {
                req.setAttribute("errorMessage", "Неверный логин или пароль");
                req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            throw new RuntimeException("ошибка проверки",e);
        }
    }
}
