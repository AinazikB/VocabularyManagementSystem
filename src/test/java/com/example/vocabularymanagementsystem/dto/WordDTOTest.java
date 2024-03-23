package com.example.vocabularymanagementsystem.dto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(SpringExtension.class)
public class WordDTOTest {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    void testValidWordDTO() {
        WordDTO wordDTO = WordDTO.builder()
                .wordId(1L)
                .originalWord("Hello")
                .translation("Hola")
                .difficultyLevel("Medium")
                .status("Active")
                .build();

        var violations = validator.validate(wordDTO);
        assertEquals(0, violations.size());
    }

    @Test
    void testInvalidWordDTO() {
        WordDTO wordDTO = WordDTO.builder()
                .wordId(1L)
                .originalWord("")
                .translation("")
                .difficultyLevel("Medium")
                .status("Active")
                .build();

        var violations = validator.validate(wordDTO);
        assertFalse(violations.isEmpty());
    }
}
