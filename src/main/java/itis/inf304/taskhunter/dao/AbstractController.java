package itis.inf304.taskhunter.dao;

import itis.inf304.taskhunter.util.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AbstractController {
    private ConnectionProvider connectionProvider;

    public AbstractController(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }
    public PreparedStatement getPrepareStatement(String sql) {
        PreparedStatement ps = null;
        try {
            ps = connectionProvider.getConnection().prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ps;
    }

    public void closePrepareStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
