package itis.inf304.taskhunter.service;

import itis.inf304.taskhunter.dao.LoginAttemptDao;
import itis.inf304.taskhunter.dao.UserDao;
import itis.inf304.taskhunter.dto.UserLoginDto;
import itis.inf304.taskhunter.dto.UserRegistrationDto;
import itis.inf304.taskhunter.entities.User;
import itis.inf304.taskhunter.util.GenerateDefaultIcon;

import javax.servlet.ServletContext;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Logger;

public class SecurityService {

    private static final Logger LOG = Logger.getLogger(SecurityService.class.getName());

    User dbUser;
    UserDao userDao;
    LoginAttemptDao loginAttemptDao;

    public SecurityService(UserDao userDao, LoginAttemptDao loginAttemptDao) {
        this.userDao = userDao;
        this.loginAttemptDao = loginAttemptDao;
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

    public boolean updateUser(User user) throws SQLException {
        return userDao.updateUser(user);
    }

    public boolean register(UserRegistrationDto user) {
        try{
            LOG.info("newUser " + user.getUsername() + " " + user.getPassword() + " " + user.getEmail() + " " + user.getPassword());
            boolean isUserCreated = userDao.newUser(new UserRegistrationDto(
                    user.getUsername(),
                    user.getEmail(),
                    user.getNumber(),
                    hashPassword(user.getPassword()),
                    GenerateDefaultIcon.generateDefaultIcon(user.getUsername())
            ));
            return isUserCreated;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public boolean auth(ServletContext context, UserLoginDto user) throws Exception {
        try {
            dbUser = userDao.getUserByEmail(user.getEmail());
            if (dbUser == null) {
                loginAttemptDao.saveLoginAttempt(user.getEmail(),false);
                return false;
            }
            if (user.getEmail().equals(dbUser.getEmail()) && hashPassword(user.getPassword()).equals(dbUser.getPassword())){
                loginAttemptDao.saveLoginAttempt(user.getEmail(),true);
                return true;
            }
            else {
                loginAttemptDao.saveLoginAttempt(user.getEmail(),false);
                return false;
            }
        } catch (Exception e){
            loginAttemptDao.saveLoginAttempt(user.getEmail(),false);
            throw new Exception("Ошибка авторизации", e);
        }
    }
}
