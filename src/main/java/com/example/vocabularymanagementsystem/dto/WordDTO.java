package com.example.vocabularymanagementsystem.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WordDTO {
    private Long wordId;

    @NotBlank(message = "Original word must not be blank")
    private String originalWord;

    @NotBlank(message = "Translation must not be blank")
    private String translation;

    @NotBlank(message = "Difficulty level must not be blank")
    private String difficultyLevel;

    @NotBlank(message = "Status must not be blank")
    private String status;
}
