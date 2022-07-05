package com.example.elearningsystem.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String url;

    public Lesson(String name, String description, String url) {
        this.name = name;
        this.description = description;
        this.url = url;
    }

    public Lesson() {

    }
}
