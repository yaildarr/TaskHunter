package itis.inf304.taskhunter.servlets;

import itis.inf304.taskhunter.service.DeleteUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteProfile")
public class DeleteProfileServlet extends HttpServlet {
    DeleteUserService deleteUserService;

    @Override
    public void init() throws ServletException {
        deleteUserService = (DeleteUserService) getServletContext().getAttribute("deleteUserService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (deleteUserService.deleteUser(req,resp)){
                resp.sendRedirect(getServletContext().getContextPath() + "/jobs");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
