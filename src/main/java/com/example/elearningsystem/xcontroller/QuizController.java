package com.example.elearningsystem.xcontroller;

import com.example.elearningsystem.model.Choice;
import com.example.elearningsystem.model.Lesson;
import com.example.elearningsystem.model.TrueFalseQuestion;
import com.example.elearningsystem.model.User;
import com.example.elearningsystem.service.TrueFalseQuestionService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/quiz")
@AllArgsConstructor
public class QuizController {

    private final TrueFalseQuestionService trueFalseQuestionService;

    @GetMapping
    public String getQuizPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<TrueFalseQuestion> trueFalseQuestions = this.trueFalseQuestionService.findAll();
        model.addAttribute("trueFalseQuestions", trueFalseQuestions);
        model.addAttribute("bodyContent", "quiz");
        return "master-template";
    }

    @GetMapping("/add-form")
    public String addQuestionPage(Model model) {
        model.addAttribute("bodyContent", "add-question");
        return "master-template";
    }

    @PostMapping("/add")
    public String addQuestion(
            @RequestParam(required = false) Long id,
            @RequestParam Integer points,
            @RequestParam String question,
            @RequestParam Choice choice) {
        if (id != null) {
            this.trueFalseQuestionService.edit(id, points, question, choice);
        } else {
            this.trueFalseQuestionService.add(points, question, choice);
        }
        return "redirect:/quiz";
    }

    @GetMapping("/question/{id}")
    public String getQuestionWithGivenId(@PathVariable Long id, Model model) {
        TrueFalseQuestion trueFalseQuestion = this.trueFalseQuestionService.findById(id);
        model.addAttribute("trueFalseQuestion", trueFalseQuestion);
        model.addAttribute("bodyContent", "question");
        return "master-template";
    }

    @PostMapping("/question/{id}")
    public String postQuestionWithGivenId(@PathVariable Long id,
                                          Authentication authentication,
                                          @RequestParam Choice choice) {
        User user = (User) authentication.getPrincipal();
        this.trueFalseQuestionService.checkAnswerOnQuestion(id, choice, user);
        List<TrueFalseQuestion> trueFalseQuestions = this.trueFalseQuestionService.findAll();
        if (id >= trueFalseQuestions.size())
            return "redirect:/lessons";
        else {
            return "redirect:/lessons/" + id + 1;
        }
    }
}
