package com.example.elearningsystem.service.impl;

import com.example.elearningsystem.model.Kviz;
import com.example.elearningsystem.model.Question;
import com.example.elearningsystem.model.User;
import com.example.elearningsystem.repository.QuestionRepository;
import com.example.elearningsystem.repository.UserRepository;
import com.example.elearningsystem.service.KvizService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class KvizServiceImpl implements KvizService {

    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    @Autowired
    Kviz kviz;

    @Override
    public Kviz getQuestions() {
        List<Question> questions = this.questionRepository.findAll();
        kviz.setQuestionList(questions);
        return kviz;
    }

    @Override
    public int getResult(Kviz kviz, User user) {
        int correct = 0;

        for(Question q : kviz.getQuestionList())
            if(q.getAns() == q.getChose())
                correct++;
        user.setPoints(correct);
        user.setQuizDone(true);
        this.userRepository.save(user);
        return correct;
    }

//    @Override
//    public void savePoints(User user) {
//        user.setPoints();
//    }
}
