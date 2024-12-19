package itis.inf304.taskhunter.service;

import itis.inf304.taskhunter.dao.JobDao;
import itis.inf304.taskhunter.dao.UserDao;
import itis.inf304.taskhunter.entities.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class DeleteUserService {

    UserDao userDao;
    JobDao jobDao;

    public DeleteUserService(UserDao userDao, JobDao jobDao) {
        this.userDao = userDao;
        this.jobDao = jobDao;
    }

    public boolean deleteUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        try {
            User user = (User) req.getSession().getAttribute("user");
            jobDao.deleteAllJobsByUserId(user.getId());
            userDao.deleteUser(user.getId());
            HttpSession session = req.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("user_id".equals(cookie.getName())) {
                        cookie.setMaxAge(0);
                        cookie.setPath("/");
                        resp.addCookie(cookie);
                    }
                }
            }
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
}
