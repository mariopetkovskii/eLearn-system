package com.example.elearningsystem.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class Kviz {
    private List<Question> questionList;
}
