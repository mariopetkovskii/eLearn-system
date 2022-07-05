package com.example.elearningsystem.service.impl;

import com.example.elearningsystem.model.Choice;
import com.example.elearningsystem.model.TrueFalseQuestion;
import com.example.elearningsystem.model.User;
import com.example.elearningsystem.model.exception.QuestionNotFoundException;
import com.example.elearningsystem.repository.TrueFalseQuestionRepository;
import com.example.elearningsystem.repository.UserRepository;
import com.example.elearningsystem.service.TrueFalseQuestionService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TrueFalseQuestionServiceImpl implements TrueFalseQuestionService {

    private final TrueFalseQuestionRepository trueFalseQuestionRepository;
    private final UserRepository userRepository;

    @Override
    public List<TrueFalseQuestion> findAll() {
        return this.trueFalseQuestionRepository.findAll();
    }

    @Override
    public Optional<TrueFalseQuestion> add(Integer points, String question, Choice choice) {
        return Optional.of(this.trueFalseQuestionRepository.save(new TrueFalseQuestion(points, question, choice)));
    }

    @Override
    public Optional<TrueFalseQuestion> edit(Long id, Integer points, String question, Choice choice) {
        TrueFalseQuestion trueFalseQuestion = this.trueFalseQuestionRepository.findById(id).orElseThrow(() -> new QuestionNotFoundException(id));
        trueFalseQuestion.setQuestion(question);
        trueFalseQuestion.setPoints(points);
        trueFalseQuestion.setChoice(choice);
        return Optional.of(this.trueFalseQuestionRepository.save(trueFalseQuestion));
    }

    @Override
    public TrueFalseQuestion findById(Long id) {
        return this.trueFalseQuestionRepository.findById(id).orElseThrow(() -> new QuestionNotFoundException(id));
    }

    @Override
    public void deleteById(Long id) {
        this.trueFalseQuestionRepository.deleteById(id);
    }

    @Override
    public TrueFalseQuestion checkAnswerOnQuestion(Long id, Choice choice, User user) {
        TrueFalseQuestion trueFalseQuestion = this.trueFalseQuestionRepository.findById(id).orElseThrow(() -> new QuestionNotFoundException(id));
        Choice getChoice = trueFalseQuestion.getChoice();
        Integer points = user.getPoints();
        Integer questionPoints = trueFalseQuestion.getPoints();
        if (getChoice == choice)
            points += questionPoints;
        else
            points += questionPoints / 2;
        user.setPoints(points);
        this.userRepository.save(user);
        return trueFalseQuestion;
    }


}
