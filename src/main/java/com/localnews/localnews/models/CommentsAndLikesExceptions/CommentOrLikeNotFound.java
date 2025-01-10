package com.localnews.localnews.models.commentsAndLikesExceptions;

import java.io.Serial;


public class CommentOrLikeNotFound extends RuntimeException {
  @Serial
  private static final long serialVersionUID = 1L;

  public CommentOrLikeNotFound(String message) {
    super(message);
  }
}
