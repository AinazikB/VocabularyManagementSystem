package com.example.vocabularymanagementsystem.dto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class DTOTest {

    @Test
    public void testLearnerDTOValidation() {
        LearnerDTO learnerDTO = LearnerDTO.builder()
                .learnerId(1L)
                .username("")
                .email("test@example.com")
                .words(Collections.singletonList(new WordDTO()))
                .build();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        var violations = validator.validate(learnerDTO);

        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(violation -> violation.getMessage().equals("Username must not be blank")));
    }

    @Test
    public void testWordDTOValidation() {
        WordDTO wordDTO = new WordDTO();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        var violations = validator.validate(wordDTO);

        assertEquals(5, violations.size());
    }
}
