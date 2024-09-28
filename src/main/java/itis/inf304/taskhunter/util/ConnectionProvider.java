package itis.inf304.taskhunter.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
    private static ConnectionProvider _instance;

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
        } catch (Exception e) {
            throw new RuntimeException("Ошибка подключения к базе данных",e);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
