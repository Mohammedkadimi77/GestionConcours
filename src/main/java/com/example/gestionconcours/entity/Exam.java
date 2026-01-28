package com.example.gestionconcours.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Ecrit, Oral
    private Double coefficient;
    private Double maxScore;

    private LocalDate examDate;

    @ManyToOne
    private Contest contest;

    // getters & setters
}
