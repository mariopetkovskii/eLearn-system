package com.example.elearningsystem.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToMany
    private List<Lesson> lessons;

    private Boolean isDone;

    public Activity(String name, String description) {
        this.name = name;
        this.description = description;
        this.lessons = new ArrayList<>();
        this.isDone = false;
    }

    public Activity(){

    }
}
