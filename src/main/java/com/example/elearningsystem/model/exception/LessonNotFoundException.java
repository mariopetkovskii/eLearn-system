package com.example.elearningsystem.model.exception;

public class LessonNotFoundException extends RuntimeException{
    public LessonNotFoundException(Long id) {
        super(String.format("Lesson with id: %d was not found!", id));
    }
}
