package org.blogapp.controllers.articles;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.blogapp.models.ArticleEntity;
import org.blogapp.models.UserEntity;
import org.blogapp.models.managers.ArticleManager;
import org.blogapp.models.managers.UserManager;
import org.blogapp.utils.UserAccess;

import java.io.IOException;


@WebServlet("/article/create")
public class ArticleCreateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!UserAccess.hasAccess(req)){
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        getServletContext().getRequestDispatcher("/create_article.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String content = req.getParameter("content");

        if(title.length() < 5 || content.length() < 30){
            resp.sendRedirect("?error=length");
            return;
        }

        UserEntity user = UserManager.init().getUserByUsername(String.valueOf(req.getSession(false).getAttribute("username")));

        ArticleEntity newArticle = new ArticleEntity();
        newArticle.setTitle(title);
        newArticle.setContent(content);
        newArticle.setUser(user);

        ArticleManager.init().addArticle(newArticle);

        resp.sendRedirect("/");
    }
}
