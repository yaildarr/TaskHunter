package itis.inf304.taskhunter.servlets;

import itis.inf304.taskhunter.dao.UserDao;
import itis.inf304.taskhunter.dto.UserLoginDto;
import itis.inf304.taskhunter.entities.User;
import itis.inf304.taskhunter.service.SecurityService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
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
            if (securityService.login(getServletContext(), new UserLoginDto(email, password))) {
                user = userDao.getUserByEmail(email);
                HttpSession session = req.getSession();
                Cookie cookie = new Cookie("user_id", user.getId()+"");
                cookie.setMaxAge(24*60*60*3);
                resp.addCookie(cookie);
                session.setAttribute("username", user.getUsername());
                session.setAttribute("user", user);
                resp.sendRedirect(getServletContext().getContextPath() + "/jobs");
            } else {
                req.setAttribute("errorMessage", "Неверный логин или пароль");
                req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            throw new RuntimeException("ошибка проверки",e);
        }
    }
}
