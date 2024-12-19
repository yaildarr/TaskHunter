package itis.inf304.taskhunter.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        performLogout(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        performLogout(req, resp);
    }

    private void performLogout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Завершение сессии
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // Удаление куки "user_id"
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("user_id".equals(cookie.getName())) {
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    resp.addCookie(cookie);
                }
            }
        }

        // Перенаправление на страницу
        resp.sendRedirect(getServletContext().getContextPath() + "/jobs");
    }
}