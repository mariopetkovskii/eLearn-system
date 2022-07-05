package com.example.elearningsystem.model.exception;

public class QuestionNotFoundException extends RuntimeException{
    public QuestionNotFoundException(Long id) {
        super(String.format("Question with id %d was not found", id));
    }
}
