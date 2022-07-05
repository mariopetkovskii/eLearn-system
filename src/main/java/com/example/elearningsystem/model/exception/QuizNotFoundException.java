package com.example.elearningsystem.model.exception;

public class QuizNotFoundException extends RuntimeException{
    public QuizNotFoundException(Long id) {
        super(String.format("Quiz with id %d was not found", id));
    }
}
