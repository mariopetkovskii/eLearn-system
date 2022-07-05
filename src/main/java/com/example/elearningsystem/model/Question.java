package com.example.elearningsystem.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "questions")
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int quesId;
    private String title;
    private String optionA;
    private String optionB;
    private String optionC;
    private int ans;
    private int chose;

    public Question(String title, String optionA, String optionB, String optionC, int ans, int chose) {
        this.title = title;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.ans = ans;
        this.chose = chose;
    }

    public Question() {
    }
}
