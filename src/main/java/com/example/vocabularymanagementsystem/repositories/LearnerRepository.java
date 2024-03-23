package com.example.vocabularymanagementsystem.repositories;

import com.example.vocabularymanagementsystem.entity.Learner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LearnerRepository extends JpaRepository<Learner, Long> {
    Optional<Learner> findByUsername(String username);
}