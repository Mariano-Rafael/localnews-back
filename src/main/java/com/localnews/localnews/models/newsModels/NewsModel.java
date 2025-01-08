package com.localnews.localnews.models.newsModels;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class NewsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private String author;

    private String sourceId;

    private String sourceName;

    @Column(nullable = false)
    private LocalDateTime publicationDate;

    @Column(columnDefinition = "TEXT")
    private String urlNews;

    public NewsModel() {
    }

    public NewsModel(Long id, String title, String content, String author, LocalDateTime publicationDate, String urlNews) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.publicationDate = publicationDate;
        this.urlNews = urlNews;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getUrlNews() {
        return urlNews;
    }

    public void setUrlNews(String urlNews) {
        this.urlNews = urlNews;
    }
}
