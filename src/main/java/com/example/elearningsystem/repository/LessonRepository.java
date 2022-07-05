package com.example.elearningsystem.repository;

import com.example.elearningsystem.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
