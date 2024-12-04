package itis.inf304.taskhunter.service;

import itis.inf304.taskhunter.dao.UserDao;
import itis.inf304.taskhunter.dto.UserForJobDto;
import itis.inf304.taskhunter.entities.User;

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
            LOG.info("Getting user by id: " + id);
            User userFromDb = userDao.getUserById(id);
            return new UserForJobDto(userFromDb.getId(),userFromDb.getUsername(),userFromDb.getNumber());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
