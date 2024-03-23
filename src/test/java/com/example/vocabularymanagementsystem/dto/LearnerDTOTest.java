package com.example.vocabularymanagementsystem.dto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class LearnerDTOTest {

    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();

    @Test
    public void whenUsernameIsNull_ValidationFails() {
        LearnerDTO learnerDTO = new LearnerDTO(null, null, null, null);
        Set<ConstraintViolation<LearnerDTO>> violations = validator.validate(learnerDTO);
        assertThat(violations).hasSize(1);
    }

    @Test
    public void whenUsernameIsBlank_ValidationFails() {
        LearnerDTO learnerDTO = new LearnerDTO(null, "", null, null);
        Set<ConstraintViolation<LearnerDTO>> violations = validator.validate(learnerDTO);
        assertThat(violations).hasSize(1);
    }

    @Test
    public void whenEmailIsNull_ValidationFails() {
        LearnerDTO learnerDTO = new LearnerDTO(null, "username", null, null);
        Set<ConstraintViolation<LearnerDTO>> violations = validator.validate(learnerDTO);
        assertThat(violations).hasSize(1);
    }

    @Test
    public void whenEmailIsInvalid_ValidationFails() {
        LearnerDTO learnerDTO = new LearnerDTO(null, "username", "invalid_email", null);
        Set<ConstraintViolation<LearnerDTO>> violations = validator.validate(learnerDTO);
        assertThat(violations).hasSize(1);
    }

    @Test
    public void whenWordsListIsNull_ValidationFails() {
        LearnerDTO learnerDTO = new LearnerDTO(null, "username", "test@example.com", null);
        Set<ConstraintViolation<LearnerDTO>> violations = validator.validate(learnerDTO);
        assertThat(violations).hasSize(1);
    }

    @Test
    public void whenWordsListIsEmpty_ValidationFails() {
        LearnerDTO learnerDTO = new LearnerDTO(null, "username", "test@example.com", new ArrayList<>());
        Set<ConstraintViolation<LearnerDTO>> violations = validator.validate(learnerDTO);
        assertThat(violations).hasSize(1);
    }


}
