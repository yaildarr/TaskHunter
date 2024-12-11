package itis.inf304.taskhunter.service;

import itis.inf304.taskhunter.dao.UserDao;
import itis.inf304.taskhunter.dto.UserLoginDto;
import itis.inf304.taskhunter.entities.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

public class LoginService {

    SecurityService securityService;
    UserDao userDao;

    private static final Logger LOG = Logger.getLogger(LoginService.class.getName());


    public LoginService(SecurityService securityService, UserDao userDao) {
        this.securityService = securityService;
        this.userDao = userDao;
    }

    public boolean login(HttpServletRequest req, HttpServletResponse resp, String email, String password) {
        try {
            if (securityService.auth(req.getServletContext(), new UserLoginDto(email, password))) {
                User user = userDao.getUserByEmail(email);
                LOG.info(user.toString());
                HttpSession session = req.getSession();
                Cookie cookie = new Cookie("user_id", user.getId()+"");
                cookie.setMaxAge(24*60*60*3);
                resp.addCookie(cookie);
                session.setAttribute("username", user.getUsername());
                session.setAttribute("user", user);
                resp.sendRedirect(req.getServletContext().getContextPath() + "/jobs");
                return true;
            } else {
                req.setAttribute("errorMessage", "Неверный логин или пароль");
                req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            throw new RuntimeException("ошибка проверки",e);
        }
        return false;
    }
}
