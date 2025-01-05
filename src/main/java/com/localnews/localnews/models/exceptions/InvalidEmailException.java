package com.localnews.localnews.models.exceptions;

import java.io.Serial;


public class InvalidEmailException extends RuntimeException {
  @Serial
  private static final long serialVersionUID = 1L;

  public InvalidEmailException(String message) {
    super(message);
  }
}
