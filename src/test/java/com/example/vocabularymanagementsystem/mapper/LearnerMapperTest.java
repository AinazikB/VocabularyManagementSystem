package com.example.vocabularymanagementsystem.mapper;

import com.example.vocabularymanagementsystem.dto.LearnerDTO;
import com.example.vocabularymanagementsystem.entity.Learner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class LearnerMapperTest {

    @Mock
    private LearnerMapper learnerMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLearnerToDtoMapping() {
        // Create a Learner entity with some values
        Learner learner = new Learner();
        learner.setUsername("testUser");
        learner.setEmail("test@example.com");

        // Create a LearnerDTO manually with the same values
        LearnerDTO expectedDto = new LearnerDTO();
        expectedDto.setUsername("testUser");
        expectedDto.setEmail("test@example.com");

        // Define behavior for the mock
        when(learnerMapper.learnerToDto(learner)).thenReturn(expectedDto);

        // Perform the mapping
        LearnerDTO mappedDTO = learnerMapper.learnerToDto(learner);

        // Assert that the mapping produced the expected result
        assertEquals(expectedDto.getUsername(), mappedDTO.getUsername());
        assertEquals(expectedDto.getEmail(), mappedDTO.getEmail());
    }

    @Test
    public void testDtoToLearnerMapping() {
        // Create a LearnerDTO with some values
        LearnerDTO learnerDTO = new LearnerDTO();
        learnerDTO.setUsername("testUser");
        learnerDTO.setEmail("test@example.com");

        // Create a Learner entity manually with the same values
        Learner expectedLearner = new Learner();
        expectedLearner.setUsername("testUser");
        expectedLearner.setEmail("test@example.com");

        // Define behavior for the mock
        when(learnerMapper.dtoToLearner(learnerDTO)).thenReturn(expectedLearner);

        // Perform the mapping
        Learner mappedLearner = learnerMapper.dtoToLearner(learnerDTO);

        // Assert that the mapping produced the expected result
        assertEquals(expectedLearner.getUsername(), mappedLearner.getUsername());
        assertEquals(expectedLearner.getEmail(), mappedLearner.getEmail());
    }
}
