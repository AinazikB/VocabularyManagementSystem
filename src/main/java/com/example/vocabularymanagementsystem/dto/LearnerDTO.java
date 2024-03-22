package com.example.vocabularymanagementsystem.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LearnerDTO {
    private Long learnerId;

    @NotBlank(message = "Username must not be blank")
    private String username;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email must not be blank")
    private String email;

    @NotNull(message = "Words list must not be null")
    @Size(min = 1, message = "At least one word must be present")
    private List<WordDTO> words;
}
