package com.example.elearningsystem.xcontroller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public String getAdminPage(@RequestParam(required = false) String error, @RequestParam(required = false) String success, Model model){
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        if(success != null && !success.isEmpty()) {
            model.addAttribute("isSuccess", true);
            model.addAttribute("success", success);
        }
        model.addAttribute("bodyContent", "adminPage");
        return "master-template";
    }
}
