package com.example.elearningsystem.xcontroller;

import com.example.elearningsystem.model.Lesson;
import com.example.elearningsystem.model.User;
import com.example.elearningsystem.service.LessonService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/lessons")
public class LessonController {

    private final LessonService lessonService;

    @GetMapping
    public String getLessonsPage(@RequestParam(required = false) String error, Model model, Authentication auth){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Lesson> lessons = this.lessonService.findAll();
        User user = (User) auth.getPrincipal();
        model.addAttribute("lessons", lessons);
        model.addAttribute("user", user);
        model.addAttribute("bodyContent", "lessons");
        return "master-template";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/add-form")
    public String addLessonPage(Model model) {
        model.addAttribute("bodyContent", "add-lesson");
        return "master-template";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public String addLesson(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam String desc,
            @RequestParam String url) {
        if (id != null) {
            this.lessonService.edit(id, name, desc, url);
        } else {
            this.lessonService.add(name, desc, url);
        }
        return "redirect:/lessons";
    }

    @GetMapping("/{id}")
    public String getLessonIdPage(@PathVariable Long id,
                                  Model model){
        Lesson lesson = this.lessonService.findById(id);
        model.addAttribute("lesson", lesson);
        model.addAttribute("bodyContent", "lesson");
        return "master-template";
    }

}
