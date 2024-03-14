package org.blogapp.controllers.users;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.blogapp.models.managers.UserManager;
import org.blogapp.utils.FormValidation;
import org.blogapp.utils.UserAccess;

import java.io.IOException;

@WebServlet("/registration")
public class UserRegistrationServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(UserAccess.loggedIn(request)){
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        request.getRequestDispatcher("/users/registration.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        UserManager manager = UserManager.init();

        if(manager.getUserByUsername(username) != null){
            response.sendRedirect("?error=userexists");
            return;
        }

        if(username.length() < 5 || username.length() > 49){
            response.sendRedirect("?error=usernamewrong");
            return;
        }

        if(!FormValidation.validateEmail(email)){
            response.sendRedirect("?error=wrongemail");
            return;
        }

        if(!FormValidation.validatePassword(password)){
            response.sendRedirect("?error=wrongpassword");
            return;
        }

        if(manager.registerUser(username, password, email)){
            HttpSession session = request.getSession(true);
            session.setAttribute("username", username);
            response.sendRedirect("/");
        }else{
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
