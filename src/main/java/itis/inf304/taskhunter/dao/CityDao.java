package itis.inf304.taskhunter.dao;

import itis.inf304.taskhunter.entities.City;
import itis.inf304.taskhunter.util.ConnectionProvider;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityDao extends AbstractController{
    public CityDao(ConnectionProvider connectionProvider) {
        super(connectionProvider);
    }

    public List<City> getCitiesByName(String name) throws SQLException {
        String sql = "SELECT id, city_name FROM cities WHERE city_name LIKE CONCAT('%', ?, '%') LIMIT 10";
        List<City> cities = new ArrayList<>();

        try (PreparedStatement ps = getPrepareStatement(sql)){
            ps.setString(1, name);

            try (ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    City city = new City(
                            rs.getInt("id"),
                            rs.getString("city_name")
                    );
                    cities.add(city);
                }
            }
        }
        return cities;
    }
}
