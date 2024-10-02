package com.aditya.schooglinkassignment;

import java.util.List;

public class Response {

    private final List<Article> articles;

    public Response(List<Article> articles) {
        this.articles = articles;
    }

    public List<Article> getArticles() {
        return articles;
    }
}



