package itis.inf304.taskhunter.service;

import itis.inf304.taskhunter.dao.UserDao;
import itis.inf304.taskhunter.entities.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class SecurityService {

    User dbUser;

    public boolean register(ServletContext context, User user) {
        try{
            context.setAttribute("newUser", user);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean login(ServletContext context, User user) throws Exception {
        try {
            UserDao dao = (UserDao) context.getAttribute("userDao");
            dbUser = dao.getUserByEmail(user.getEmail());
            return user.getEmail().equals(dbUser.getEmail()) && user.getPassword().equals(dbUser.getPassword());
        } catch (Exception e){
            throw new Exception("Ошибка авторизации", e);
        }
    }
//    public static boolean logout(HttpServletRequest resp) {
//
//    }
}
