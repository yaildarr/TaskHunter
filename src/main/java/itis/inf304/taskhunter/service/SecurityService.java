package itis.inf304.taskhunter.service;

import itis.inf304.taskhunter.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecurityService {

    public static boolean register(HttpServletRequest resp, User user) {
        try{
            resp.setAttribute("User", user);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public static boolean login(HttpServletRequest req, HttpServletResponse resp, User user) {
        if (req.getAttribute("User") != null) {

        }
    }
    public static boolean logout(HttpServletRequest resp) {

    }
    pu
}
