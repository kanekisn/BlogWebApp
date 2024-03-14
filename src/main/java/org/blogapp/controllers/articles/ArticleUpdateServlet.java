package org.blogapp.controllers.articles;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.blogapp.models.ArticleEntity;
import org.blogapp.models.managers.ArticleManager;
import org.blogapp.utils.ArticleRenderPage;
import org.blogapp.utils.UserAccess;

import java.io.IOException;

@WebServlet("/article/update/*")
public class ArticleUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!UserAccess.hasAccess(req)){
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        if (ArticleRenderPage.renderPage(req, resp)) return;
        getServletContext().getRequestDispatcher("/update_article.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String slug = req.getParameter("slug");

        ArticleManager manager = ArticleManager.init();
        ArticleEntity entity = manager.getArticleBySlug(slug);
        entity.setTitle(title);
        entity.setContent(content);
        manager.updateArticle(entity);

        resp.sendRedirect("/");
    }
}
