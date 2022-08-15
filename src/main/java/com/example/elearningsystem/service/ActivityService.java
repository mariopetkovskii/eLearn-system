package com.example.elearningsystem.service;

import com.example.elearningsystem.model.Activity;
import com.example.elearningsystem.model.Lesson;
import com.example.elearningsystem.model.User;

import java.util.List;
import java.util.Optional;

public interface ActivityService {

    List<Activity> findAll();

    Optional<Activity> add(String name, String desc);

    Activity findById(Long id);

    void deleteById(Long id);

    Optional<Activity> addLesson(Long activityId, Long id);

    void doneActivity(Long id, User user);

}
