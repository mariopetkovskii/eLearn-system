package com.example.elearningsystem.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "questions")
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;
    private String a;
    private String b;
    private String c;
    private String d;
    private Integer answer;
    private Integer choose;

    public Question(String question, String a, String b, String c, String d, Integer answer) {
        this.question = question;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.answer = answer;
        this.choose = -1;
    }

    public Question() {
    }
}
