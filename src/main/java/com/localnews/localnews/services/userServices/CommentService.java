package com.localnews.localnews.services.userServices;

import com.localnews.localnews.models.BooleanResponseModel;
import com.localnews.localnews.models.PollModels.PollModel;
import com.localnews.localnews.models.commentsAndLikesExceptions.CommentOrLikeNotFound;
import com.localnews.localnews.models.commentsAndLikesExceptions.NewsOrUserNotFoundException;
import com.localnews.localnews.models.userModels.CommentDTO;
import com.localnews.localnews.models.userModels.CommentModel;
import com.localnews.localnews.models.userModels.UserModel;
import com.localnews.localnews.repositories.pollRepositories.PollRepository;
import com.localnews.localnews.repositories.userRepositories.CommentRepository;
import com.localnews.localnews.repositories.userRepositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PollRepository pollRepository;


    private void validateCommentAssociations(CommentModel comment) {
        if (comment.getUserModel() == null || comment.getUserModel().getId() == null) {
            throw new CommentOrLikeNotFound("Usuário do comentário não informado.");
        }
        if (comment.getPollModel() == null || comment.getPollModel().getId() == null) {
            throw new CommentOrLikeNotFound("Enquete do comentário não informada.");
        }

        UserModel user = userRepository.findById(comment.getUserModel().getId())
                .orElseThrow(() -> new CommentOrLikeNotFound("Usuário não encontrado"));
        PollModel poll = pollRepository.findById(comment.getPollModel().getId())
                .orElseThrow(() -> new CommentOrLikeNotFound("Enquete não encontrada"));

        comment.setUserModel(user);
        comment.setPollModel(poll);
    }

    // posta novo comentário
    public CommentModel createComment(CommentModel commentModel) {
        if (commentModel.getComment() == null || commentModel.getComment().isEmpty()) {
            throw new CommentOrLikeNotFound("Comentário inválido.");
        }

        validateCommentAssociations(commentModel);

        return commentRepository.save(commentModel);
    }

    // atualiza comentário pelo id do mesmo
    public CommentModel updateComment(Long id, CommentModel commentModel) {
        if (commentModel.getUserModel() == null || commentModel.getPollModel() == null) {
            throw new NewsOrUserNotFoundException("Necessário informar o comentário e a enquete.");
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

    // retorna comentários
    public List<CommentDTO> getCommentsByPollId(Long pollId) {
        List<CommentModel> comments = commentRepository.findByPollModel_IdOrderByCreatedAtAsc(pollId);
        return comments.stream().map(comment -> new CommentDTO(
                comment.getId(),
                comment.getComment(),
                comment.getUserModel().getId(),
                comment.getUserModel().getUsername(),
                comment.getPollModel().getId(),
                comment.getCreatedAt()
        )).collect(Collectors.toList());
    }
}

