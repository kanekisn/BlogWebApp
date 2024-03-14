package org.blogapp.utils;

import org.blogapp.models.ArticleEntity;

import java.util.List;

public class Pagination {
    private final int pageSize;
    private final List<ArticleEntity> entities;

    public Pagination(int pageSize, List<ArticleEntity> entities){
        this.pageSize = pageSize;
        this.entities = entities;
    }

    public List<ArticleEntity> getPageItems(int pageNumber) {
        if (entities == null || entities.isEmpty()) return null;

        int listSize = entities.size();
        int totalPages = (int) Math.ceil((double) listSize / pageSize);

        if (pageNumber < 1 || pageNumber > totalPages) return null;

        int startIndex = (pageNumber - 1) * pageSize;
        int endIndex = Math.min(pageNumber * pageSize, listSize);
        return entities.subList(startIndex, endIndex);
    }

    public Integer getTotalPages(){
        return (int) Math.ceil((double) entities.size() / pageSize);
    }

    public boolean isLastPage(int currentPage){
        return getTotalPages() == currentPage;
    }

    public boolean isFirstPage(int currentPage){
        return currentPage == 1;
    }
}
