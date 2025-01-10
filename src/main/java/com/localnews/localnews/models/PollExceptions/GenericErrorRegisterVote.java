package com.localnews.localnews.models.PollExceptions;

import java.io.Serial;


public class GenericErrorRegisterVote extends RuntimeException {
  @Serial
  private static final long serialVersionUID = 1L;

  public GenericErrorRegisterVote(String message) {
    super(message);
  }
}
