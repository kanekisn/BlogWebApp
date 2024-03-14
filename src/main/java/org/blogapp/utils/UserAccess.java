package org.blogapp.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.blogapp.models.UserType;
import org.blogapp.models.managers.UserManager;

public class UserAccess {
    public static boolean hasAccess(HttpServletRequest req){
        HttpSession session = req.getSession(false);
        if (session.getAttribute("username") == null) {
            return false;
        }
        return UserManager.init().getUserByUsername(String.valueOf(session.getAttribute("username"))).getUserType() != UserType.USER;
    }

    public static boolean loggedIn(HttpServletRequest req){
        HttpSession session = req.getSession(false);
        return session.getAttribute("username") != null;
    }
}
