package org.blogapp.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.blogapp.models.ArticleEntity;
import org.blogapp.models.managers.ArticleManager;
import org.blogapp.utils.Pagination;

import java.io.IOException;
import java.util.List;

@WebServlet("")
public class HomePageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ArticleEntity> articles = ArticleManager.init().getAllArticles();
        Pagination pagination = new Pagination(4, articles);
        req.setAttribute("pagination", pagination);
        if(req.getParameter("page") == null) req.setAttribute("articles", pagination.getPageItems(1));
        else req.setAttribute("articles", pagination.getPageItems(Integer.parseInt(req.getParameter("page"))));
        if(req.getParameter("page") != null){
            int curPage = Integer.parseInt(req.getParameter("page"));
            if(curPage > pagination.getTotalPages() || curPage <= 0){
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
        }
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}

