package com.localnews.localnews.models.exceptions;

import java.io.Serial;


public class UsernameOrEmailAlreadyExistsException extends RuntimeException {
  @Serial
  private static final long serialVersionUID = 1L;

  public UsernameOrEmailAlreadyExistsException(String message) {
    super(message);
  }
}
