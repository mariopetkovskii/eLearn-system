package com.example.elearningsystem.service;

import com.example.elearningsystem.model.Lesson;

import java.util.List;
import java.util.Optional;

public interface LessonService {
    List<Lesson> findAll();

    Optional<Lesson> add(String name, String desc, String url);

    Optional<Lesson> edit(Long id, String name, String desc, String url);

    Lesson findById(Long id);

    void deleteById(Long id);
}
