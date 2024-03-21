package com.example.vocabularymanagementsystem.mapper;

import com.example.vocabularymanagementsystem.dto.WordDTO;
import com.example.vocabularymanagementsystem.entity.Word;
import com.example.vocabularymanagementsystem.enums.WordStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class WordMapperTest {

    @Mock
    private WordMapper wordMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testWordToDtoMapping() {
        // Create a Word entity with some values
        Word word = new Word();
        word.setOriginalWord("hello");
        word.setTranslation("hola");
        word.setDifficultyLevel("Easy");
        word.setStatus(WordStatus.NEW); // Use the enum value

        // Create a WordDTO manually with the same values
        WordDTO expectedDto = new WordDTO();
        expectedDto.setOriginalWord("hello");
        expectedDto.setTranslation("hola");
        expectedDto.setDifficultyLevel("Easy");
        expectedDto.setStatus("NEW"); // Use the enum value

        // Define behavior for the mock
        when(wordMapper.wordToDto(word)).thenReturn(expectedDto);

        // Perform the mapping
        WordDTO mappedDTO = wordMapper.wordToDto(word);

        // Assert that the mapping produced the expected result
        assertEquals(expectedDto.getOriginalWord(), mappedDTO.getOriginalWord());
        assertEquals(expectedDto.getTranslation(), mappedDTO.getTranslation());
        assertEquals(expectedDto.getDifficultyLevel(), mappedDTO.getDifficultyLevel());
        assertEquals(expectedDto.getStatus(), mappedDTO.getStatus());
    }

    @Test
    public void testDtoToWordMapping() {
        // Create a WordDTO with some values
        WordDTO wordDTO = new WordDTO();
        wordDTO.setOriginalWord("hello");
        wordDTO.setTranslation("hola");
        wordDTO.setDifficultyLevel("Easy");
        wordDTO.setStatus("NEW"); // Use the enum value

        // Create a Word entity manually with the same values
        Word expectedWord = new Word();
        expectedWord.setOriginalWord("hello");
        expectedWord.setTranslation("hola");
        expectedWord.setDifficultyLevel("Easy");
        expectedWord.setStatus(WordStatus.NEW); // Use the enum value

        // Define behavior for the mock
        when(wordMapper.dtoToWord(wordDTO)).thenReturn(expectedWord);

        // Perform the mapping
        Word mappedWord = wordMapper.dtoToWord(wordDTO);

        // Assert that the mapping produced the expected result
        assertEquals(expectedWord.getOriginalWord(), mappedWord.getOriginalWord());
        assertEquals(expectedWord.getTranslation(), mappedWord.getTranslation());
        assertEquals(expectedWord.getDifficultyLevel(), mappedWord.getDifficultyLevel());
        assertEquals(expectedWord.getStatus(), mappedWord.getStatus());
    }
}
