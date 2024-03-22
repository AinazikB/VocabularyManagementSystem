package com.example.vocabularymanagementsystem.controllers;

import com.example.vocabularymanagementsystem.entity.Word;
import com.example.vocabularymanagementsystem.services.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/words")
@Api(tags = "Word Management", description = "Endpoints for managing words")
public class WordController {

    @Autowired
    private WordService wordService;

    @GetMapping
    @ApiOperation(value = "Get all words", response = List.class)
    public List<Word> getAllWords() {
        return wordService.getAllWords();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a word by ID", response = Word.class)
    public Word getWordById(@PathVariable @ApiParam(value = "Word ID", example = "1") Long id) {
        return wordService.getWordById(id);
    }

    @PostMapping
    @ApiOperation(value = "Create a new word", response = Word.class)
    public Word createWord(@RequestBody @ApiParam(value = "New word details") Word word) {
        return wordService.createWord(word);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update an existing word", response = Word.class)
    public Word updateWord(@PathVariable @ApiParam(value = "Word ID", example = "1") Long id,
                           @RequestBody @ApiParam(value = "Updated word details") Word wordDetails) {
        return wordService.updateWord(id, wordDetails);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a word by ID")
    public void deleteWord(@PathVariable @ApiParam(value = "Word ID", example = "1") Long id) {
        wordService.deleteWord(id);
    }
}
