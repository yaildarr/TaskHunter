package itis.inf304.taskhunter;

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
            sce.getServletContext().setAttribute("userDao", new UserDao(connectionProvider));
            sce.getServletContext().setAttribute("securityService", new SecurityService());
        } catch (Exception e) {
            throw new RuntimeException("Ошибка иницализации" , e);
        }
    }
}
