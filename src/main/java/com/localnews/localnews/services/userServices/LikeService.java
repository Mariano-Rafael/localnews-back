package com.localnews.localnews.services.userServices;

import com.localnews.localnews.models.CommentsAndLikesExceptions.CommentOrLikeNotFound;
import com.localnews.localnews.models.userModels.LikeModel;
import com.localnews.localnews.repositories.userRepositories.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    // registra o like
    public LikeModel createLike(LikeModel likeModel) {
        if (likeModel.getUserModel() == null || likeModel.getNewsModel() == null) {
            throw new CommentOrLikeNotFound("Necessário informar o comentário e a noticia.");
        }
        return likeRepository.save(likeModel);
    }

    // retira o like
    public void unlike(Long id) {
        if (!likeRepository.existsById(id)) {
            throw new CommentOrLikeNotFound("Sem like registrado para a notícia.");
        }
        likeRepository.deleteById(id);
    }

    // total de likes por noticia
    public int countLikes(Long id) {
        return likeRepository.countByNewsId(id);
    }
}
