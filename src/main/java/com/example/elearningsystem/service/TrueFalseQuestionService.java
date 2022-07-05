package com.example.elearningsystem.service;

import com.example.elearningsystem.model.Choice;
import com.example.elearningsystem.model.TrueFalseQuestion;
import com.example.elearningsystem.model.User;

import java.util.List;
import java.util.Optional;

public interface TrueFalseQuestionService {
    List<TrueFalseQuestion> findAll();

    Optional<TrueFalseQuestion> add(Integer points, String question, Choice choice);

    Optional<TrueFalseQuestion> edit(Long id, Integer points, String question, Choice choice);

    TrueFalseQuestion findById(Long id);

    void deleteById(Long id);

    TrueFalseQuestion checkAnswerOnQuestion(Long id, Choice choice, User user);
}
