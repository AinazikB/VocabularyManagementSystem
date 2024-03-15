package com.example.vocabularymanagementsystem.mapper;

import com.example.vocabularymanagementsystem.dto.LearnerDTO;
import com.example.vocabularymanagementsystem.entity.Learner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LearnerMapperTest {

    private final LearnerMapper learnerMapper = LearnerMapper.INSTANCE;

    @Test
    void testLearnerToDto() {
        Learner learner = new Learner();
        learner.setLearnerId(1L);
        learner.setUsername("testuser");
        learner.setEmail("test@example.com");

        LearnerDTO learnerDTO = learnerMapper.learnerToDto(learner);

        assertEquals(1L, learnerDTO.getLearnerId());
        assertEquals("testuser", learnerDTO.getUsername());
        assertEquals("test@example.com", learnerDTO.getEmail());
    }

    @Test
    void testDtoToLearner() {
        LearnerDTO learnerDTO = new LearnerDTO();
        learnerDTO.setLearnerId(1L);
        learnerDTO.setUsername("testuser");
        learnerDTO.setEmail("test@example.com");

        Learner learner = learnerMapper.dtoToLearner(learnerDTO);

        assertEquals(1L, learner.getLearnerId());
        assertEquals("testuser", learner.getUsername());
        assertEquals("test@example.com", learner.getEmail());
    }
}
