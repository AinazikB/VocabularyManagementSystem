package com.example.vocabularymanagementsystem.mapper;

import com.example.vocabularymanagementsystem.dto.WordDTO;
import com.example.vocabularymanagementsystem.entity.Word;
import com.example.vocabularymanagementsystem.enums.WordStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordMapperTest {

    private WordMapper wordMapper;

    @BeforeEach
    public void setUp() {
        wordMapper = Mappers.getMapper(WordMapper.class);
    }

    @Test
    public void testWordToDto() {
        Word entity = new Word();
        entity.setWordId(1L);
        entity.setOriginalWord("Test");
        entity.setTranslation("Тест");
        entity.setDifficultyLevel("Medium");
        entity.setStatus(WordStatus.NEW);

        WordDTO dto = wordMapper.wordToDto(entity);
        dto.setStatus(WordStatus.NEW.name());

        assertEquals(entity.getWordId(), dto.getWordId());
        assertEquals(entity.getOriginalWord(), dto.getOriginalWord());
        assertEquals(entity.getTranslation(), dto.getTranslation());
        assertEquals(entity.getDifficultyLevel(), dto.getDifficultyLevel());
        assertEquals(WordStatus.NEW.name(), dto.getStatus());
    }

    @Test
    public void testDtoToWord() {
        WordDTO dto = new WordDTO();
        dto.setWordId(1L);
        dto.setOriginalWord("Test");
        dto.setTranslation("Тест");
        dto.setDifficultyLevel("Medium");
        dto.setStatus(WordStatus.NEW.name());

        Word entity = wordMapper.dtoToWord(dto);

        assertEquals(dto.getWordId(), entity.getWordId());
        assertEquals(dto.getOriginalWord(), entity.getOriginalWord());
        assertEquals(dto.getTranslation(), entity.getTranslation());
        assertEquals(dto.getDifficultyLevel(), entity.getDifficultyLevel());
        assertEquals(WordStatus.NEW, WordStatus.valueOf(dto.getStatus()));
    }
}
