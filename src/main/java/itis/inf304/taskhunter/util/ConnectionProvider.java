package itis.inf304.taskhunter.util;

import itis.inf304.taskhunter.servlets.CreateJobServlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ConnectionProvider {
    private static ConnectionProvider _instance;

    private static final Logger LOG = Logger.getLogger(ConnectionProvider.class.getName());


    public static ConnectionProvider getInstance() {
        if (_instance == null) {
            _instance = new ConnectionProvider();
        }
        return _instance;
    }
    private Connection connection;

    private ConnectionProvider(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/taskhunter", "root", "546741qwerty");
            LOG.info("Successfully connected to database");
            LOG.info("Successfully connected to dataaaabase");
        } catch (Exception e) {
            throw new RuntimeException("Ошибка подключения к базе данных",e);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
