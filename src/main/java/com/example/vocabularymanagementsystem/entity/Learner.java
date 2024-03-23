package com.example.vocabularymanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Learner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long learnerId;

    private String username;

    private String email;

    private String password;

    @OneToMany(mappedBy = "learner", cascade = CascadeType.ALL)
    private List<Word> words = new ArrayList<>();

    @OneToMany(mappedBy = "learner", cascade = CascadeType.ALL)
    private Set<UserRole> roles = new HashSet<>();
}
