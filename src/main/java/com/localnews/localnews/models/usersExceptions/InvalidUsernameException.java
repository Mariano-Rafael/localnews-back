package com.localnews.localnews.models.usersExceptions;

import java.io.Serial;

public class InvalidUsernameException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidUsernameException(String message) {
        super(message);
    }
}
