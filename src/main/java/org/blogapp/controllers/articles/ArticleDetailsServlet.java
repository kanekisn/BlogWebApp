package org.blogapp.controllers.articles;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.blogapp.utils.ArticleRenderPage;

import java.io.IOException;

@WebServlet("/article/*")
public class ArticleDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (ArticleRenderPage.renderPage(req, resp)) return;
        getServletContext().getRequestDispatcher("/details_article.jsp").forward(req, resp);
    }
}
