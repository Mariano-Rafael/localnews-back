package com.localnews.localnews.models.usersExceptions;

import java.io.Serial;


public class UsernamelAlreadyExistsException extends RuntimeException {
  @Serial
  private static final long serialVersionUID = 1L;

  public UsernamelAlreadyExistsException(String message) {
    super(message);
  }
}
