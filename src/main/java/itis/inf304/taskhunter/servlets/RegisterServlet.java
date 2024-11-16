package itis.inf304.taskhunter.servlets;

import itis.inf304.taskhunter.dao.UserDao;
import itis.inf304.taskhunter.entities.User;
import itis.inf304.taskhunter.service.SecurityService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

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
        getServletContext().getRequestDispatcher("/WEB-INF/view/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String number = req.getParameter("number");
        user = new User(email, password, username, number);
        try{
            if(securityService.register(getServletContext(),user)){
                HttpSession session = req.getSession();
                session.setAttribute("username", username);
                session.setAttribute("user", user);
                resp.sendRedirect(getServletContext().getContextPath()+"/api/jobs");
            } else {
                req.setAttribute("errorMessage", "Не удалось зарегестрироваться");
                req.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(req, resp);
            }
        } catch (Exception e){
            throw new ServletException("Не удалось зарегестрироваться", e);
        }
    }
}
