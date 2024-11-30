package itis.inf304.taskhunter;

import itis.inf304.taskhunter.dao.JobDao;
import itis.inf304.taskhunter.dao.LoginAttemptDao;
import itis.inf304.taskhunter.dao.MessageDao;
import itis.inf304.taskhunter.dao.UserDao;
import itis.inf304.taskhunter.service.SecurityService;
import itis.inf304.taskhunter.util.ConnectionProvider;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class initListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try{
            ConnectionProvider connectionProvider = ConnectionProvider.getInstance();
            sce.getServletContext().setAttribute("loginAttemptDao", new LoginAttemptDao(connectionProvider));
            sce.getServletContext().setAttribute("userDao", new UserDao(connectionProvider));
            sce.getServletContext().setAttribute("securityService", new SecurityService((UserDao) sce.getServletContext().getAttribute("userDao"), (LoginAttemptDao) sce.getServletContext().getAttribute("loginAttemptDao")));
            sce.getServletContext().setAttribute("jobDao", new JobDao(connectionProvider));
            sce.getServletContext().setAttribute("messageDao", new MessageDao(connectionProvider));
        } catch (Exception e) {
            throw new RuntimeException("Ошибка иницализации" , e);
        }
    }
}
