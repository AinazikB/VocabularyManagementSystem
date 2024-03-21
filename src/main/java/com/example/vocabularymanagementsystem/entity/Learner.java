package com.example.vocabularymanagementsystem.entity;

import com.example.vocabularymanagementsystem.enums.WordStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Learner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long learnerId;

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Size(min = 6, max = 255, message = "Email length must be between 6 and 255 characters")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 255, message = "Password length must be between 8 and 255 characters")
    private String password;

    @OneToMany(mappedBy = "learner", cascade = CascadeType.ALL)
    private List<Word> words = new ArrayList<>();
}
