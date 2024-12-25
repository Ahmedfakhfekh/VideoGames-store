package com.example.video_games.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidGameDataException extends RuntimeException {
    public InvalidGameDataException(String message) {
        super(message);
    }
}