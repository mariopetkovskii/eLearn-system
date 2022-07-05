package com.example.elearningsystem.service;

import com.example.elearningsystem.model.Kviz;
import com.example.elearningsystem.model.Question;
import com.example.elearningsystem.model.User;

import java.util.List;

public interface KvizService {
    Kviz getQuestions();

    int getResult(Kviz kviz, User user);

//    void savePoints(User user);
}
