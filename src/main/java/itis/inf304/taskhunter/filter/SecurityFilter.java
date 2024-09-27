package itis.inf304.taskhunter.filter;

import itis.inf304.taskhunter.entities.User;

import javax.servlet.http.HttpServletRequest;

public class SecurityFilter {

    public static boolean isSigned(HttpServletRequest req) {
        return (req.getAttribute("account") == null);
    }

}
