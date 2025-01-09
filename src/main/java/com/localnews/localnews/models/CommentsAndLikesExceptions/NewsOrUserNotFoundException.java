package com.localnews.localnews.models.CommentsAndLikesExceptions;

import java.io.Serial;


public class NewsOrUserNotFoundException extends RuntimeException {
  @Serial
  private static final long serialVersionUID = 1L;

  public NewsOrUserNotFoundException(String message) {
    super(message);
  }
}
