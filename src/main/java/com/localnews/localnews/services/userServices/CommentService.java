package com.localnews.localnews.services.userServices;

import com.localnews.localnews.models.BooleanResponseModel;
import com.localnews.localnews.models.CommentsAndLikesExceptions.CommentOrLikeNotFound;
import com.localnews.localnews.models.CommentsAndLikesExceptions.NewsOrUserNotFoundException;
import com.localnews.localnews.models.userModels.CommentModel;
import com.localnews.localnews.repositories.userRepositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    // posta novo comentário
    public CommentModel createComment(CommentModel commentModel) {
        if (commentModel.getUserModel() == null || commentModel.getNewsModel() == null) {
            throw new CommentOrLikeNotFound("Necessário informar o comentário e a noticia.");
        }
        if (commentModel.getComment() == null || commentModel.getComment().isEmpty()) {
            throw new CommentOrLikeNotFound("Comentário inválido.");
        }
        return commentRepository.save(commentModel);
    }

    // atualiza comentário pelo id do mesmo
    public CommentModel updateComment(Long id, CommentModel commentModel) {
        if (commentModel.getUserModel() == null || commentModel.getNewsModel() == null) {
            throw new NewsOrUserNotFoundException("Necessário informar o comentário e a noticia.");
        }
        if (commentModel.getComment() == null || commentModel.getComment().isEmpty()) {
            throw new CommentOrLikeNotFound("Comentário inválido.");
        }
        return commentRepository.findById(id)
                .map(existingComment -> {
                    existingComment.setComment(commentModel.getComment());
                    return commentRepository.save(existingComment);
                })
                .orElseThrow(() -> new RuntimeException(String.valueOf(new BooleanResponseModel(false,
                        "Comentário nao encontrado"))));
    }

    // deleta comentário pelo id do mesmo
    public void deleteComment(Long id) {
        if (!commentRepository.existsById(id)) {
            throw new CommentOrLikeNotFound("Comentário nao encontrado.");
        }
        commentRepository.deleteById(id);
    }
}

