package itis.inf304.taskhunter.service;

import itis.inf304.taskhunter.dao.UserDao;
import itis.inf304.taskhunter.dto.UserLoginDto;
import itis.inf304.taskhunter.entities.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
                cookie.setPath("/");
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

    public boolean checkUserSessionAndCookie(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            // Получаем куки
            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("user_id".equals(cookie.getName())) {
                        // Проверяем, есть ли кука с идентификатором пользователя
                        String userId = cookie.getValue();
                        if (userId != null && !userId.isEmpty()) {
                            // Получаем пользователя из сессии
                            HttpSession session = req.getSession(false);
                            if (session != null) {
                                User user = (User) session.getAttribute("user");
                                if (user != null && (user.getId() == Integer.parseInt(userId))) {
                                    return true;
                                }
                            } if (session == null) {
                                req.getSession().setAttribute("user", userDao.getUserById(Integer.parseInt(userId)));
                                return true;
                            }
                        }
                    } else{
                        User user = (User) req.getSession().getAttribute("user");
                        if (user != null) {
                            Cookie newCookie = new Cookie("user_id", user.getId()+"");
                            cookie.setMaxAge(24*60*60*3);
                            resp.addCookie(cookie);
                            return true;
                        }
                    }
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // Если куки нет или сессия не содержит нужного пользователя, перенаправляем на страницу логина
        resp.sendRedirect(req.getServletContext().getContextPath() + "/login");
        return false;
    }

}
