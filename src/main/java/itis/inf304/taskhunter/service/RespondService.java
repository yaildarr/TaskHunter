package itis.inf304.taskhunter.service;

import itis.inf304.taskhunter.dao.RespondDao;
import itis.inf304.taskhunter.entities.User;
import org.cloudinary.json.JSONException;
import org.cloudinary.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

public class RespondService {

    RespondDao respondDao;

    public RespondService(RespondDao respondDao) {
        this.respondDao = respondDao;
    }

    public void checkRespond(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        StringBuilder jsonBuffer = new StringBuilder();
        String line;
        try (BufferedReader reader = req.getReader()) {
            while ((line = reader.readLine()) != null) {
                jsonBuffer.append(line);
            }
        }

        // Парсим JSON
        String json = jsonBuffer.toString();
        int jobId = 0;
        try {
            JSONObject jsonObject = new JSONObject(json);
            jobId = jsonObject.getInt("jobId");
        } catch (JSONException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        // Получаем пользователя из сессии
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        int userId = ((User) session.getAttribute("user")).getId();

        respondDao.postRespond(userId,jobId);

        // Устанавливаем статус 200 (успех)
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    public int checkCountRespond(int jobId) throws SQLException {
        return respondDao.getRespondCountById(jobId);
    }
}
