package com.localnews.localnews.models.userModels;

import com.localnews.localnews.models.newsModels.NewsModel;
import jakarta.persistence.*;

@Entity
public class LikeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel userModel;

    @ManyToOne
    @JoinColumn(name = "news_id", nullable = false)
    private NewsModel newsModel;

    @Column(name = "news_id", nullable = false, insertable = false, updatable = false)
    private Long newsId;

    public LikeModel() {
    }

    public LikeModel(Long id, Long newsId, UserModel userModel, NewsModel newsModel) {
        this.id = id;
        this.userModel = userModel;
        this.newsModel = newsModel;
        this.newsId = newsId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }
}
