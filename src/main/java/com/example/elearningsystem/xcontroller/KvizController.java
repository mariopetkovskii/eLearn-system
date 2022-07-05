package com.example.elearningsystem.xcontroller;

import com.example.elearningsystem.model.Kviz;
import com.example.elearningsystem.model.User;
import com.example.elearningsystem.service.KvizService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/kviz")
public class KvizController {

    private final KvizService kvizService;

    Boolean submitted = false;
    @Autowired
    Kviz kviz;

    @ModelAttribute("kviz")
    public Kviz getKviz(){
        return kviz;
    }

    public KvizController(KvizService kvizService) {
        this.kvizService = kvizService;
    }

    @GetMapping
    public String quiz(Model model){
        Kviz kviz = kvizService.getQuestions();
        model.addAttribute("bodyContent", "kviz");
        model.addAttribute("prasanja", kviz);
        return "master-template";
    }

    @PostMapping("/submit")
    public String submit(@ModelAttribute Kviz kviz, Model model, Authentication auth){
        User user = (User) auth.getPrincipal();
        if(!submitted){
            kvizService.getResult(kviz, user);
            submitted = true;
        }
        model.addAttribute("user", user);
        return "redirect:/lessons";
    }
}
