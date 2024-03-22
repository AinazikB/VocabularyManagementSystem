package com.example.vocabularymanagementsystem.mapper;

import com.example.vocabularymanagementsystem.dto.LearnerDTO;
import com.example.vocabularymanagementsystem.entity.Learner;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LearnerMapperTest {

    @Test
    public void testLearnerToDtoMapping() {
        // Create a Learner entity with some values
        Learner learner = new Learner();
        learner.setLearnerId(1L);
        learner.setUsername("testUser");
        learner.setEmail("test@example.com");

        // Perform the mapping
        LearnerDTO mappedDTO = LearnerMapper.INSTANCE.learnerToDto(learner);

        // Assert that the mapping produced the expected result
        assertEquals(learner.getLearnerId(), mappedDTO.getLearnerId());
        assertEquals(learner.getUsername(), mappedDTO.getUsername());
        assertEquals(learner.getEmail(), mappedDTO.getEmail());
    }

    @Test
    public void testDtoToLearnerMapping() {
        // Create a LearnerDTO with some values
        LearnerDTO learnerDTO = new LearnerDTO();
        learnerDTO.setLearnerId(1L);
        learnerDTO.setUsername("testUser");
        learnerDTO.setEmail("test@example.com");

        // Perform the mapping
        Learner mappedLearner = LearnerMapper.INSTANCE.dtoToLearner(learnerDTO);

        // Assert that the mapping produced the expected result
        assertEquals(learnerDTO.getLearnerId(), mappedLearner.getLearnerId());
        assertEquals(learnerDTO.getUsername(), mappedLearner.getUsername());
        assertEquals(learnerDTO.getEmail(), mappedLearner.getEmail());
    }
}
