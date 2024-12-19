package itis.inf304.taskhunter.servlets;

import itis.inf304.taskhunter.service.CityService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLException;
import java.util.logging.Logger;


@WebServlet("/searchCity")
public class CitySearchServlet extends HttpServlet {

    CityService cityService;
    private static final Logger LOG = Logger.getLogger(CitySearchServlet.class.getName());

    @Override
    public void init() throws ServletException {
        cityService = (CityService) getServletContext().getAttribute("cityService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            cityService.selectCity(request,response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
