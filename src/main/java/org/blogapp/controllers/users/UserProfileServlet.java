package org.blogapp.controllers.users;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.blogapp.models.UserEntity;
import org.blogapp.models.managers.UserManager;
import org.blogapp.utils.UserAccess;

import java.io.IOException;

@WebServlet("/user/profile/*")
public class UserProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!UserAccess.loggedIn(req)){
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        String requestURI = req.getRequestURI();
        String username = requestURI.substring(requestURI.lastIndexOf("/") + 1);
        UserEntity userEntity = UserManager.init().getUserByUsername(username);

        if(userEntity == null){
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        req.setAttribute("user", userEntity);
        getServletContext().getRequestDispatcher("/users/profile.jsp").forward(req, resp);
    }
}
