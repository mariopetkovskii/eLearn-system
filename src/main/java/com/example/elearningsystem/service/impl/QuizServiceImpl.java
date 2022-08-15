package com.example.elearningsystem.service.impl;

import com.example.elearningsystem.model.Question;
import com.example.elearningsystem.model.Quiz;
import com.example.elearningsystem.model.User;
import com.example.elearningsystem.model.exception.QuestionNotFoundException;
import com.example.elearningsystem.repository.QuestionRepository;
import com.example.elearningsystem.repository.UserRepository;
import com.example.elearningsystem.service.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    @Autowired
    Quiz quiz;

    @Override
    public Quiz getQuestions() {
        List<Question> questions = this.questionRepository.findAll();
        List<Question> randomQuestions = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            Integer num = random.nextInt(questions.size());
            randomQuestions.add(questions.get(num));
            questions.remove(questions.get(num));
        }
        quiz.setQuestionList(randomQuestions);
        return quiz;
    }

    @Override
    public int getResult(Quiz quiz, User user) {
        int correct = 0;

        for (Question q : quiz.getQuestionList())
            if (q.getAnswer() == q.getChoose())
                correct++;
        user.setPoints(correct);
        user.setQuizDone(true);
        this.userRepository.save(user);
        return correct;
    }

    @Override
    public int getResultTest(Quiz quiz) {
        int correct = 0;

        for (Question q : quiz.getQuestionList())
            if (q.getAnswer() == q.getChoose())
                correct++;
        return correct;
    }

    @Override
    public List<Question> findAll() {
        return this.questionRepository.findAll();
    }

    @Override
    public Optional<Question> add(String question, String a, String b, String c, String d, Integer answer) {
        return Optional.of(this.questionRepository.save(new Question(question, a, b, c, d, answer)));
    }

    @Override
    public Optional<Question> edit(Long id, String question, String a, String b, String c, String d, Integer answer) {
        Question question1 = this.questionRepository.findById(id).orElseThrow(() -> new QuestionNotFoundException(id));
        question1.setQuestion(question);
        question1.setA(a);
        question1.setAnswer(answer);
        question1.setB(b);
        question1.setC(c);
        question1.setD(d);
        return Optional.of(this.questionRepository.save(question1));
    }

    @Override
    public void deleteById(Long id) {
        this.questionRepository.deleteById(id);
    }


}
