package com.example.vocabularymanagementsystem.controllers;

import com.example.vocabularymanagementsystem.entity.Word;
import com.example.vocabularymanagementsystem.services.WordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class WordControllerTest {

    @Mock
    WordService wordService;

    @InjectMocks
    WordController wordController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllWords() {
        List<Word> words = Arrays.asList(new Word(), new Word());
        when(wordService.getAllWords()).thenReturn(words);

        List<Word> result = wordController.getAllWords();

        assertEquals(2, result.size());
        verify(wordService, times(1)).getAllWords();
    }

    @Test
    void getWordById() {
        Word word = new Word();
        word.setWordId(1L);
        when(wordService.getWordById(1L)).thenReturn(word);

        Word result = wordController.getWordById(1L);

        assertEquals(word, result);
        verify(wordService, times(1)).getWordById(1L);
    }

    @Test
    void createWord() {
        Word word = new Word();
        when(wordService.createWord(any(Word.class))).thenReturn(word);

        Word result = wordController.createWord(new Word());

        assertEquals(word, result);
        verify(wordService, times(1)).createWord(any(Word.class));
    }

    @Test
    void updateWord() {
        Word existingWord = new Word();
        existingWord.setWordId(1L);
        when(wordService.updateWord(1L, existingWord)).thenReturn(existingWord);

        Word result = wordController.updateWord(1L, existingWord);

        assertEquals(existingWord, result);
        verify(wordService, times(1)).updateWord(1L, existingWord);
    }

    @Test
    void deleteWord() {
        wordController.deleteWord(1L);

        verify(wordService, times(1)).deleteWord(1L);
    }
}
