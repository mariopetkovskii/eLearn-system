package com.example.elearningsystem.xcontroller;

import com.example.elearningsystem.model.Quiz;
import com.example.elearningsystem.model.User;
import com.example.elearningsystem.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/quiz")
public class QuizController {

    private final QuizService quizService;

    Boolean submitted = false;
    @Autowired
    Quiz quiz;

    @ModelAttribute("quiz")
    public Quiz getQuiz(){
        return quiz;
    }

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/test")
    public String test(Model model){
        Quiz quiz = quizService.getQuestions();
        model.addAttribute("bodyContent", "lessonTest");
        model.addAttribute("questions", quiz);
        return "master-template";
    }

    @GetMapping("/takeQuiz")
    public String quiz(Model model){
        Quiz quiz = quizService.getQuestions();
        model.addAttribute("bodyContent", "quiz");
        model.addAttribute("questions", quiz);
        return "master-template";
    }

//    @PostMapping("/test")
//    public String test(@ModelAttribute Quiz quiz, Model model){
//        Integer result = quizService.getResultTest(quiz);
//        model.addAttribute("result", result);
//        return "redirect:/quiz/getResult?success=Imate osvoeno "+result + " poeni";
//    }

    @PostMapping("/test")
    public String test(@ModelAttribute Quiz quiz, Model model){
        Integer result = quizService.getResultTest(quiz);
        model.addAttribute("result", result);
        return "redirect:/quiz/getResult?success=Osvoivte "+result + " poeni na kvizot sto go resavavte. Ovie poeni ne vleguvaat vo glavniot kviz koj sluzi za generiranje na sertifikatot.";
    }

    @GetMapping("/getResult")
    public String testResult(@RequestParam(required = false) String success,
                             @ModelAttribute Quiz quiz, Model model){
        if(success != null && !success.isEmpty()) {
            model.addAttribute("isSuccess", true);
            model.addAttribute("success", success);
        }
        model.addAttribute("bodyContent", "getResultFromTest");
        return "master-template";
    }

    @PostMapping("/submit")
    public String submit(@ModelAttribute Quiz quiz, Model model, Authentication auth){
        User user = (User) auth.getPrincipal();
        quizService.getResult(quiz, user);
        model.addAttribute("user", user);
        return "redirect:/activity";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/question/add-form")
    public String getAddForm(Model model){
        model.addAttribute("bodyContent", "addQuestion");
        return "master-template";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/question/add")
    public String addQuestion(@RequestParam(required = false) Long id,
                              @RequestParam String question,
                              @RequestParam String a,
                              @RequestParam String b,
                              @RequestParam String c,
                              @RequestParam String d,
                              @RequestParam Integer answer){
        if(id != null){
            this.quizService.edit(id, question, a, b, c, d, answer);
        }else
            this.quizService.add(question, a, b, c, d, answer);
        return "redirect:/quiz/questions";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/questions")
    public String getQuestions(Model model){
        model.addAttribute("bodyContent", "questions");
        model.addAttribute("questions", this.quizService.findAll());
        return "master-template";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/question/delete/{id}")
    public String deleteQuestion(@PathVariable Long id){
        this.quizService.deleteById(id);
        return "redirect:/quiz/questions";
    }


}
