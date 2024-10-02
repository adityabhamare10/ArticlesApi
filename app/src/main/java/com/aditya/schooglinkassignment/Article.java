package com.aditya.schooglinkassignment;

import java.io.Serializable;

public class Article implements Serializable {
    private final String author;
    private final String title;
    private final String description;
    private final String urlToImage;
    private final String publishedAt;
    private final String content;

    public Article(String author, String title, String description, String urlToImage, String publishedAt, String content) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }


    public String getDescription() {
        return description;
    }


    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getContent() {
        return content;
    }

}


