package itis.inf304.taskhunter.service;

import itis.inf304.taskhunter.dao.UserDao;
import itis.inf304.taskhunter.dto.UserForJobDto;
import itis.inf304.taskhunter.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Logger;

public class UserService {

    private static final Logger LOG = Logger.getLogger(UserService.class.getName());

    UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserForJobDto getUserForJobById(Integer id) throws SQLException {
        try{
            User userFromDb = userDao.getUserById(id);
            return new UserForJobDto(userFromDb.getId(),userFromDb.getUsername(),userFromDb.getNumber(),userFromDb.getPhotoURL());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public User getUserById(Integer id) throws SQLException {
        try{
            User userFromDb = userDao.getUserById(id);
            return userFromDb;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean updatePassword(HttpServletRequest req, HttpServletResponse resp) throws SQLException, NoSuchAlgorithmException, IOException {
        String currentPassword = req.getParameter("currentPassword");
        String newPassword = req.getParameter("newPassword");
        String confirmPassword = req.getParameter("confirmPassword");User user = (User) req.getSession().getAttribute("user");

        // Валидация текущего пароля
        if (!userDao.validatePassword(user.getId(), hashPassword(currentPassword))) {
            resp.getWriter().println("{ \"error\" : \"Текущий пароль введён неверно.\" }");

            return false;
        }

        // Проверка совпадения нового пароля и подтверждения
        if (!newPassword.equals(confirmPassword)) {
            resp.getWriter().println("{ \"error\" : \"Новый пароль и подтверждение не совпадают.\" }");
            return false;
        }

        // Валидация нового пароля
        if (!isValidPassword(newPassword)) {
            resp.getWriter().println("{ \"error\" : \"Новый пароль не соответствует требованиям. Минимум 8 символов, хотя бы одна заглавная буква, одна строчная буква, одна цифра и один специальный символ.\" }");

            return false;
        }

        // Обновление пароля
        if (userDao.updatePassword(user.getId(), hashPassword(newPassword))) {
            // Обновляем информацию о пользователе в сессии
            req.getSession().setAttribute("user", userDao.getUserById(user.getId()));
            resp.getWriter().println("{ \"success\" : true }");
            return true;
        } else {
            resp.getWriter().println("{ \"error\" : \"Не удалось обновить пароль. Попробуйте позже.\" }");
            return false;
        }
    }


    public String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hashedBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    private boolean isValidPassword(String password) {
//        if (password == null || password.length() < 8) {
//            return false; // Длина пароля должна быть не менее 8 символов
//        }
//
//        // Регулярное выражение: минимум 1 заглавная буква, 1 строчная буква, 1 цифра, 1 спецсимвол
//        String passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
//        return password.matches(passwordPattern);
        if (password == null){
            return false;
        } else {
            return true;
        }
    }


}
