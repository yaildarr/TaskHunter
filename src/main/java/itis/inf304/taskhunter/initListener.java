package itis.inf304.taskhunter;

import itis.inf304.taskhunter.dao.*;
import itis.inf304.taskhunter.service.*;
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
            sce.getServletContext().setAttribute("jobService", new JobService((JobDao) sce.getServletContext().getAttribute("jobDao")));
            sce.getServletContext().setAttribute("userService", new UserService((UserDao) sce.getServletContext().getAttribute("userDao")));
            sce.getServletContext().setAttribute("jobCategoryDao", new JobCategoryDao(connectionProvider));
            sce.getServletContext().setAttribute("jobCategoryService", new JobCategoryService((JobCategoryDao) sce.getServletContext().getAttribute("jobCategoryDao")));
            sce.getServletContext().setAttribute("createJobService", new CreateJobService((JobDao) sce.getServletContext().getAttribute("jobDao")));
            sce.getServletContext().setAttribute("loginService", new LoginService((SecurityService) sce.getServletContext().getAttribute("securityService"), (UserDao) sce.getServletContext().getAttribute("userDao")));
        } catch (Exception e) {
            throw new RuntimeException("Ошибка иницализации" , e);
        }
    }
}
