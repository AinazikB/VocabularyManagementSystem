package com.example.vocabularymanagementsystem.repositories;

import com.example.vocabularymanagementsystem.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
}
