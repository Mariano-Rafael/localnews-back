package com.localnews.localnews.services.userServices;

import com.localnews.localnews.models.userModels.CommentModel;
import com.localnews.localnews.repositories.newsRepositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public CommentModel createComment(CommentModel commentModel) {
        return commentRepository.save(commentModel);
    }
}
