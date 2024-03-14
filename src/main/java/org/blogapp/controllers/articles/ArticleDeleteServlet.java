package org.blogapp.controllers.articles;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.blogapp.models.managers.ArticleManager;
import org.blogapp.utils.ArticleRenderPage;
import org.blogapp.utils.UserAccess;

import java.io.IOException;

@WebServlet("/article/delete/*")
public class ArticleDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!UserAccess.hasAccess(req)){
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        if (ArticleRenderPage.renderPage(req, resp)) return;
        getServletContext().getRequestDispatcher("/delete_article.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        if(!ArticleManager.init().deleteArticleById(id)){
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        resp.sendRedirect("/");
    }
}
