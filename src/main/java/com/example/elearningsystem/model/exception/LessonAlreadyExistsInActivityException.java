package com.example.elearningsystem.model.exception;

public class LessonAlreadyExistsInActivityException extends RuntimeException{
    public LessonAlreadyExistsInActivityException(String message) {
        super(message);
    }
}
