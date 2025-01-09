package com.localnews.localnews.models.userModels;

import com.localnews.localnews.models.newsModels.NewsModel;
import jakarta.persistence.*;

@Entity
public class CommentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel userModel;

    @ManyToOne
    @JoinColumn(name = "news_id", nullable = false)
    private NewsModel newsModel;

    public CommentModel() {
    }

    public CommentModel(Long id, String comment, UserModel userModel, NewsModel newsModel) {
        this.id = id;
        this.comment = comment;
        this.userModel = userModel;
        this.newsModel = newsModel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public NewsModel getNewsModel() {
        return newsModel;
    }

    public void setNewsModel(NewsModel newsModel) {
        this.newsModel = newsModel;
    }
}
