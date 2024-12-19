package itis.inf304.taskhunter.servlets;

import itis.inf304.taskhunter.dao.UserDao;
import itis.inf304.taskhunter.dto.UserLoginDto;
import itis.inf304.taskhunter.entities.User;
import itis.inf304.taskhunter.service.LoginService;
import itis.inf304.taskhunter.service.SecurityService;
import itis.inf304.taskhunter.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    LoginService loginService;
    UserService userService;

    @Override
    public void init() throws ServletException {
        loginService = (LoginService) getServletContext().getAttribute("loginService");
        userService = (UserService) getServletContext().getAttribute("userService");
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        req.setAttribute("title","Логин");
        if (session.getAttribute("user") != null && session.getAttribute("user") instanceof User) {
            resp.sendRedirect(getServletContext().getContextPath() + "/jobs");
        } else {
            Cookie[] cookie = req.getCookies();
            if (cookie != null) {
                for (Cookie c : cookie) {
                    if (c.getName().equals("user_id")) {
                        try {
                            session.setAttribute("user", userService.getUserById(Integer.valueOf(c.getValue())));
                            resp.sendRedirect(getServletContext().getContextPath() + "/jobs");
                            return;
                        } catch (SQLException e) {
                            System.err.println(e.getMessage());
                        }
                    }
                }
            }
            getServletContext().getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        loginService.login(req,resp,email,password);
    }
}
