package itis.inf304.taskhunter.service;

import com.google.gson.Gson;
import itis.inf304.taskhunter.dao.CityDao;
import itis.inf304.taskhunter.entities.City;
import itis.inf304.taskhunter.servlets.JobDetailServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class CityService {

    CityDao cityDao;

    private static final Logger LOG = Logger.getLogger(CityService.class.getName());

    public CityService(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    public boolean selectCity(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        try {
            String query = req.getParameter("query");
            List<City> cities = cityDao.getCitiesByName(query);
            resp.setContentType("application/json");
            PrintWriter out = resp.getWriter();
            LOG.info(new Gson().toJson(cities));
            out.println(new Gson().toJson(cities));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
