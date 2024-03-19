package com.example.vocabularymanagementsystem.controllers;

import com.example.vocabularymanagementsystem.entity.Word;
import com.example.vocabularymanagementsystem.services.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/words")
public class WordController {

    @Autowired
    private WordService wordService;

    @GetMapping
    public List<Word> getAllWords() {
        return wordService.getAllWords();
    }

    @GetMapping("/{id}")
    public Word getWordById(@PathVariable Long id) {
        return wordService.getWordById(id);
    }

    @PostMapping
    public Word createWord(@RequestBody Word word) {
        return wordService.createWord(word);
    }

    @PutMapping("/{id}")
    public Word updateWord(@PathVariable Long id, @RequestBody Word wordDetails) {
        return wordService.updateWord(id, wordDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteWord(@PathVariable Long id) {
        wordService.deleteWord(id);
    }

}