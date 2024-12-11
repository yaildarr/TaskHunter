package itis.inf304.taskhunter.servlets;

import itis.inf304.taskhunter.dao.UserDao;
import itis.inf304.taskhunter.dto.UserLoginDto;
import itis.inf304.taskhunter.entities.User;
import itis.inf304.taskhunter.service.LoginService;
import itis.inf304.taskhunter.service.SecurityService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    LoginService loginService;

    @Override
    public void init() throws ServletException {
        loginService = (LoginService) getServletContext().getAttribute("loginService");
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);
//        HttpSession session = req.getSession();
//        if (session.getAttribute("user") != null) {
//            resp.sendRedirect(getServletContext().getContextPath() + "/jobs");
//        } else {
//            Cookie[] cookie = req.getCookies();
//            if (cookie != null) {
//                for (Cookie c : cookie) {
//                    if (c.getName().equals("user")) {
//                        session.setAttribute("user", user);
//                    }
//                }
//            }
//        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        loginService.login(req,resp,email,password);
    }
}
