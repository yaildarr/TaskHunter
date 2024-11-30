package itis.inf304.taskhunter.servlets;

import itis.inf304.taskhunter.dao.MessageDao;
import itis.inf304.taskhunter.dao.UserDao;
import itis.inf304.taskhunter.entities.Message;
import itis.inf304.taskhunter.entities.User;
import itis.inf304.taskhunter.service.SecurityService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/send-message")
public class SendMessageServlet extends HttpServlet {

    MessageDao messageDao;

    @Override
    public void init() throws ServletException {
        messageDao = (MessageDao) getServletContext().getAttribute("messageDao");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String message = req.getParameter("message");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            try {
                messageDao.sendMessage(new Message(0,name,email,message));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            messageDao.sendMessage(new Message(user.getId(),name,email,message));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
