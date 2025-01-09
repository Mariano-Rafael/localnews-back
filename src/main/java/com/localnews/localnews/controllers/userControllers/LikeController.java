package com.localnews.localnews.controllers.userControllers;

import com.localnews.localnews.models.BooleanResponseModel;
import com.localnews.localnews.models.CommentsAndLikesExceptions.CommentOrLikeNotFound;
import com.localnews.localnews.models.userModels.LikeModel;
import com.localnews.localnews.services.newsServices.NewsService;
import com.localnews.localnews.services.userServices.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/likes")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @Autowired
    private NewsService newsService;

    @PostMapping("/register")
    public ResponseEntity<?> createLike(@RequestBody LikeModel likeModel) {
        try {
            LikeModel createdLike = likeService.createLike(likeModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(new BooleanResponseModel(true,
                    "Like registrado."));
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

    @DeleteMapping("/unlike")
    public ResponseEntity<?> unlike(@PathVariable Long id) {
        try {
            likeService.unlike(id);
            return ResponseEntity.ok(new BooleanResponseModel(true,
                    "Like retirado."));
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

    @GetMapping("/info/{id}")
    public ResponseEntity<?> countLikesByNewsId(@PathVariable Long id) {
        try {
            int likeCount = likeService.countLikes(id);
            return ResponseEntity.ok(new BooleanResponseModel(true,
                    "Quantidade de likes: " + likeCount));
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
