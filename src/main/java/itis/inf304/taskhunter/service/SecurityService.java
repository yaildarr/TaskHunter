package itis.inf304.taskhunter.service;

import itis.inf304.taskhunter.dao.UserDao;
import itis.inf304.taskhunter.entities.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class SecurityService {

    User dbUser;
    UserDao dao;


    public String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hashedBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public boolean register(ServletContext context, User user) {
        try{
            dao = (UserDao) context.getAttribute("userDao");
            boolean isUserCreated = dao.newUser(new User(
                    user.getEmail(),
                    hashPassword(user.getPassword()),
                    user.getUsername(),
                    user.getNumber()
            ));
            return true;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public boolean login(ServletContext context, User user) throws Exception {
        try {
            dao = (UserDao) context.getAttribute("userDao");
            dbUser = dao.getUserByEmail(user.getEmail());
            return user.getEmail().equals(dbUser.getEmail()) && hashPassword(user.getPassword()).equals(dbUser.getPassword());
        } catch (Exception e){
            throw new Exception("Ошибка авторизации", e);
        }
    }
//    public static boolean logout(HttpServletRequest resp) {
//
//    }
}
