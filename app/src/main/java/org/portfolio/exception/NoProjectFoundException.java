package org.portfolio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoProjectFoundException extends RuntimeException{
    public NoProjectFoundException(String message) {
        super(message);
    }
}
