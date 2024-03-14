package org.blogapp.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.blogapp.models.ArticleEntity;
import org.blogapp.models.managers.ArticleManager;

import java.io.IOException;

public class ArticleRenderPage {
    public static boolean renderPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String requestURI = req.getRequestURI();
        String slug = requestURI.substring(requestURI.lastIndexOf("/") + 1);
        ArticleEntity article = ArticleManager.init().getArticleBySlug(slug);

        if(article == null){
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return true;
        }

        req.setAttribute("article", article);
        return false;
    }
}
