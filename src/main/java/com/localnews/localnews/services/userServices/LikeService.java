package com.localnews.localnews.services.userServices;

import com.localnews.localnews.models.commentsAndLikesExceptions.CommentOrLikeNotFound;
import com.localnews.localnews.models.commentsAndLikesExceptions.NewsOrUserNotFoundException;
import com.localnews.localnews.models.newsModels.NewsModel;
import com.localnews.localnews.repositories.newsRepositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeService {

    @Autowired
    private NewsRepository newsRepository;

    // registra o like
    public void addLike(Long newsId) {
        Optional<NewsModel> newsOptional = newsRepository.findById(newsId);

        if (newsOptional.isPresent()) {
            NewsModel news = newsOptional.get();
            news.setLikesCount(news.getLikesCount() + 1);
            newsRepository.save(news);
        } else {
            throw new NewsOrUserNotFoundException("Noticia nao encontrada.");
        }
    }

    // retira o like
    public void removeLike(Long newsId) {
        Optional<NewsModel> newsOptional = newsRepository.findById(newsId);

        if (newsOptional.isPresent()) {
            NewsModel news = newsOptional.get();

            if (news.getLikesCount() > 0) {
                news.setLikesCount(news.getLikesCount() - 1);
                newsRepository.save(news);
            } else {
                throw new CommentOrLikeNotFound("Noticia nao possui like.");
            }
        } else {
            throw new NewsOrUserNotFoundException("Noticia nao encontrada.");
        }
    }

    // total de likes por noticia
    public int countLikesByNewsId(Long newsId) {
        Optional<NewsModel> newsOptional = newsRepository.findById(newsId);

        if (newsOptional.isPresent()) {
            return newsOptional.get().getLikesCount();
        } else {
            throw new NewsOrUserNotFoundException("Noticia nao encontrada.");
        }
    }
}
