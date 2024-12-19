package itis.inf304.taskhunter.servlets;

import itis.inf304.taskhunter.dao.UserDao;
import itis.inf304.taskhunter.dto.UserRegistrationDto;
import itis.inf304.taskhunter.entities.User;
import itis.inf304.taskhunter.service.SecurityService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    SecurityService securityService;
    UserDao userDao;
    UserRegistrationDto user;

    @Override
    public void init() throws ServletException {
        securityService = (SecurityService) getServletContext().getAttribute("securityService");
        userDao = (UserDao) getServletContext().getAttribute("userDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/view/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String number = req.getParameter("phone");
        user = new UserRegistrationDto(username, email, number, password);
        try{
            if(securityService.register(user)){
                User userId = userDao.getUserByEmail(email);
                HttpSession session = req.getSession();
                Cookie cookie = new Cookie("user_id", userId.getId()+"");
                cookie.setMaxAge(24*60*60*3);
                cookie.setPath("/");
                resp.addCookie(cookie);
                session.setAttribute("username", userId.getUsername());
                session.setAttribute("user", userId);
                resp.sendRedirect(getServletContext().getContextPath()+"/jobs");
            } else {
                req.setAttribute("errorMessage", "Не удалось зарегестрироваться");
                req.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(req, resp);
            }
        } catch (Exception e){
            throw new ServletException("Не удалось зарегестрироваться", e);
        }
    }
}
