package com.example.elearningsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class Quiz {
    private List<Question> questionList;
}
