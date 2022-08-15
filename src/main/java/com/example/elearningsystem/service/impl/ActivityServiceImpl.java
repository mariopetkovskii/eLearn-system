package com.example.elearningsystem.service.impl;

import com.example.elearningsystem.model.Activity;
import com.example.elearningsystem.model.Lesson;
import com.example.elearningsystem.model.User;
import com.example.elearningsystem.model.exception.ActivityNotFoundException;
import com.example.elearningsystem.model.exception.LessonAlreadyExistsInActivityException;
import com.example.elearningsystem.model.exception.LessonNotFoundException;
import com.example.elearningsystem.repository.ActivityRepository;
import com.example.elearningsystem.repository.LessonRepository;
import com.example.elearningsystem.repository.UserRepository;
import com.example.elearningsystem.service.ActivityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;
    private final LessonRepository lessonRepository;
    private final UserRepository userRepository;

    @Override
    public List<Activity> findAll() {
        return this.activityRepository.findAll();
    }

    @Override
    public Optional<Activity> add(String name, String desc) {
        return Optional.of(this.activityRepository.save(new Activity(name, desc)));
    }

    @Override
    public Activity findById(Long id) {
        return this.activityRepository.findById(id).orElseThrow(()
                -> new ActivityNotFoundException("Activity Not Found"));
    }

    @Override
    public void deleteById(Long id) {
        this.activityRepository.deleteById(id);
    }

    @Override
    public Optional<Activity> addLesson(Long activityId, Long id) {
        Lesson lesson = this.lessonRepository.findById(id).orElseThrow(() -> new LessonNotFoundException(id));
        Activity activity = this.activityRepository.findById(activityId).orElseThrow(() -> new ActivityNotFoundException("Activity not found"));
        if(activity.getLessons().contains(lesson))
            throw new LessonAlreadyExistsInActivityException("Lesson already exists in this activity");
        activity.getLessons().add(lesson);
        return Optional.of(this.activityRepository.save(activity));
    }

    @Override
    public void doneActivity(Long id, User user) {
        Activity activity = this.activityRepository.findById(id).orElseThrow(()
                -> new ActivityNotFoundException("Activity not found"));
        user.getActivityList().add(activity);
        this.userRepository.save(user);
    }
}
