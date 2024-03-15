package com.example.vocabularymanagementsystem.dto;

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
    private String originalWord;
    private String translation;
    private String difficultyLevel;
    private String status;
}
