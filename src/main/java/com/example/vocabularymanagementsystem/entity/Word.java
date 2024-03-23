package com.example.vocabularymanagementsystem.entity;

import com.example.vocabularymanagementsystem.enums.WordStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wordId;

    @NotBlank(message = "Original word is required")
    private String originalWord;

    @NotBlank(message = "Translation is required")
    private String translation;

    @NotBlank(message = "Difficulty level is required")
    private String difficultyLevel;

    @NotNull(message = "Status is required")
    @Enumerated(EnumType.STRING)
    private WordStatus status;

    @ManyToOne
    @JoinColumn(name = "learner_id")
    private Learner learner;
}
