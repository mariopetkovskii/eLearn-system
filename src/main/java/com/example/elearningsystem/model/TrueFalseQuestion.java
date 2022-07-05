package com.example.elearningsystem.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class TrueFalseQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer points;

    private String question;

    private Choice choice;

    public TrueFalseQuestion(Integer points, String question, Choice choice) {
        this.points = points;
        this.question = question;
        this.choice = choice;
    }

    public TrueFalseQuestion(){

    }
}
