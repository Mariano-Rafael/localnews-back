package com.localnews.localnews.controllers.userControllers;

import com.localnews.localnews.models.BooleanResponseModel;
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

    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody CommentModel commentModel) {
        try {
            CommentModel createdComment = commentService.createComment(commentModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(new BooleanResponseModel(true, "Comentário criado com sucesso."));
    }
        catch (RuntimeException e) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new BooleanResponseModel(false, e.getMessage()));
        }
    }
}
