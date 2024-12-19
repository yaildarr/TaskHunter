package itis.inf304.taskhunter.servlets;

import itis.inf304.taskhunter.service.RespondService;
import org.cloudinary.json.JSONException;
import org.cloudinary.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

@WebServlet("/respondJob")
public class RespondJobServlet extends HttpServlet {

    RespondService respondService;
    private static final Logger LOG = Logger.getLogger(RespondJobServlet.class.getName());


    @Override
    public void init() throws ServletException {
        respondService = (RespondService) getServletContext().getAttribute("respondService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("doPost RESPONDJOBSERVLET");
        try {
            respondService.checkRespond(req,resp);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
