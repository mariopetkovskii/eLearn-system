package com.example.elearningsystem.service.impl;

import com.example.elearningsystem.model.Lesson;
import com.example.elearningsystem.model.exception.LessonNotFoundException;
import com.example.elearningsystem.repository.LessonRepository;
import com.example.elearningsystem.service.LessonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;

    @Override
    public List<Lesson> findAll() {
        return this.lessonRepository.findAll();
    }

    @Override
    public Optional<Lesson> add(String name, String desc, String url) {
        return Optional.of(this.lessonRepository.save(new Lesson(name, desc, url)));
    }

    @Override
    public Optional<Lesson> edit(Long id, String name, String desc, String url) {
        Lesson lesson = this.lessonRepository.findById(id).orElseThrow(() -> new LessonNotFoundException(id));
        lesson.setName(name);
        lesson.setDescription(desc);
        lesson.setUrl(url);
        return Optional.of(this.lessonRepository.save(lesson));
    }

    @Override
    public Lesson findById(Long id) {
        return this.lessonRepository.findById(id).orElseThrow(()-> new LessonNotFoundException(id));
    }

    @Override
    public void deleteById(Long id) {
        this.lessonRepository.deleteById(id);
    }
}
