package com.example.vocabularymanagementsystem.entity;

import com.example.vocabularymanagementsystem.enums.WordStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wordId;

    private String originalWord;
    private String translation;
    private String difficultyLevel;

    @Enumerated(EnumType.STRING)
    private WordStatus status;

    @ManyToOne
    @JsonIgnore
    private Learner learner;
}

