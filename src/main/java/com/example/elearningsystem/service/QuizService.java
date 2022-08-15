package com.example.elearningsystem.service;

import com.example.elearningsystem.model.Question;
import com.example.elearningsystem.model.Quiz;
import com.example.elearningsystem.model.User;

import java.util.List;
import java.util.Optional;

public interface QuizService {
    Quiz getQuestions();

    int getResult(Quiz quiz, User user);

    int getResultTest(Quiz quiz);

    List<Question> findAll();

    Optional<Question> add(String question, String a, String b, String c, String d, Integer answer);

    Optional<Question> edit(Long id, String question, String a, String b, String c, String d, Integer answer);

    void deleteById(Long id);
}
