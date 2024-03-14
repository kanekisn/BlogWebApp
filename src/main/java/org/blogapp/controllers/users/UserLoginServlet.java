package org.blogapp.controllers.users;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.blogapp.models.managers.UserManager;
import org.blogapp.utils.UserAccess;

import java.io.IOException;

@WebServlet("/login")
public class UserLoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(UserAccess.loggedIn(request)){
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        request.getRequestDispatcher("/users/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserManager manager = UserManager.init();

        if(manager.authenticateUser(username, password)){
            HttpSession session = request.getSession(true);
            session.setAttribute("username", username);
            response.sendRedirect("/");
        }else {
            response.sendRedirect("?error=invalid");
        }
    }
}