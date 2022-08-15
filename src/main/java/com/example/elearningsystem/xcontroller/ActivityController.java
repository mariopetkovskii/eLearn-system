package com.example.elearningsystem.xcontroller;

import com.example.elearningsystem.model.Activity;
import com.example.elearningsystem.model.Lesson;
import com.example.elearningsystem.model.User;
import com.example.elearningsystem.model.exception.LessonAlreadyExistsInActivityException;
import com.example.elearningsystem.service.ActivityService;
import com.example.elearningsystem.service.LessonService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/activity")
@AllArgsConstructor
public class ActivityController {

    private final ActivityService activityService;
    private final LessonService lessonService;

    @GetMapping
    public String getActivityPage(@RequestParam(required = false) String error, @RequestParam(required = false) String success, Model model, Authentication auth){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        if (success != null && !success.isEmpty()) {
            model.addAttribute("isSuccess", true);
            model.addAttribute("success", success);
        }
        List<Activity> activities = this.activityService.findAll();
        User user = (User) auth.getPrincipal();
        model.addAttribute("activities", activities);
        model.addAttribute("user", user);
        model.addAttribute("bodyContent", "activities");
        return "master-template";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/add-form")
    public String getAddPage(Model model){
        model.addAttribute("bodyContent", "add-activity");
        return "master-template";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public String addActivity(@RequestParam String name,
                              @RequestParam String desc){
        this.activityService.add(name, desc);
        return "redirect:/activity";
    }

    @GetMapping("/{id}")
    public String getSpecifiedActivity(@RequestParam(required = false) String success, @PathVariable Long id, Model model){
        if(success != null && !success.isEmpty()) {
            model.addAttribute("isSuccess", true);
            model.addAttribute("success", success);
        }
        Activity activity = this.activityService.findById(id);
        model.addAttribute("activity", activity);
        model.addAttribute("bodyContent", "idActivity");
        return "master-template";
    }

    @GetMapping("/{id}/lessons/add")
    public String getLessonsPage(@RequestParam(required = false) String error,@PathVariable Long id, Model model){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        Activity activity = this.activityService.findById(id);
        List<Lesson> lessons = this.lessonService.findAll();
        model.addAttribute("activity", activity);
        model.addAttribute("lessons", lessons);
        model.addAttribute("bodyContent", "activityLessonsAdd");
        return "master-template";
    }

    @GetMapping("/{id}/lessons")
    public String getLessonsForActivity(@RequestParam(required = false) String error,@PathVariable Long id, Model model){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        Activity activity = this.activityService.findById(id);
        List<Lesson> lessons = activity.getLessons();
        model.addAttribute("activity", activity);
        model.addAttribute("lessons", lessons);
        model.addAttribute("bodyContent", "activityLessons");
        return "master-template";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/{activityId}/lesson/{id}")
    public String addLessonToActivity(@PathVariable Long activityId,
                                      @PathVariable Long id){
        try{
            this.activityService.addLesson(activityId, id);

        }catch (LessonAlreadyExistsInActivityException e){
            return "redirect:/activity/" + activityId + "?error=" + e.getMessage();
        }
        return "redirect:/activity/" + activityId;
    }

    @PostMapping("/{id}/done")
    public String doneActivity(@PathVariable Long id, Authentication auth){
        User user = (User) auth.getPrincipal();
        this.activityService.doneActivity(id, user);
        return "redirect:/activity";
    }
}
