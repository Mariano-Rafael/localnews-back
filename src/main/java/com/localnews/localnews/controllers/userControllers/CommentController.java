package com.localnews.localnews.controllers.userControllers;

import com.localnews.localnews.models.BooleanResponseModel;
import com.localnews.localnews.models.commentsAndLikesExceptions.CommentOrLikeNotFound;
import com.localnews.localnews.models.commentsAndLikesExceptions.NewsOrUserNotFoundException;
import com.localnews.localnews.models.userModels.CommentModel;
import com.localnews.localnews.services.userServices.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/create")
    public ResponseEntity<?> createComment(@RequestBody CommentModel commentModel) {
        try {
            CommentModel createdComment = commentService.createComment(commentModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(new BooleanResponseModel(true,
                    "Comentário criado com sucesso."));
        }
        catch (CommentOrLikeNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BooleanResponseModel(false,
                    e.getMessage()));
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new BooleanResponseModel(false,
                    "Erro interno."));
        }
    }

    // atualiza um comentário pelo id do mesmo
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateComment(@PathVariable Long id, @RequestBody CommentModel commentModel) {
        try {
            CommentModel updatedComment = commentService.updateComment(id, commentModel);
            return ResponseEntity.ok(new BooleanResponseModel(true,
                    "Comentário atualizado com sucesso."));
        }
        catch (CommentOrLikeNotFound | NewsOrUserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BooleanResponseModel(false,
                    e.getMessage()));
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new BooleanResponseModel(false,
                    "Erro interno."));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {
        try {
            commentService.deleteComment(id);
            return ResponseEntity.ok(new BooleanResponseModel(true,
                    "Comentário deletado com sucesso."));
        }
        catch (CommentOrLikeNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BooleanResponseModel(false,
                    e.getMessage()));
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new BooleanResponseModel(false,
                    "Erro interno."));
        }
    }
}
