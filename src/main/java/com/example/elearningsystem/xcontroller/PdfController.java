package com.example.elearningsystem.xcontroller;

import com.example.elearningsystem.model.User;
import com.example.elearningsystem.service.UserService;
import com.example.elearningsystem.service.pdfservice.PdfService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@AllArgsConstructor
public class PdfController {

    private final PdfService pdfService;
    private final UserService userService;

    @GetMapping("/pdf/generate")
    public void generatePDF(HttpServletResponse response, Authentication auth) throws IOException {
        response.setContentType("application/pdf");

        String headerKey = "Content-Disposition";
        User user = (User) auth.getPrincipal();
        String headerValue = "attachment; filename=certificate_" + user.getName() + user.getSurname() + ".pdf";
        response.setHeader(headerKey, headerValue);

        this.pdfService.export(response, user);
    }
}