package com.example.vocabularymanagementsystem.controllers;

import com.example.vocabularymanagementsystem.entity.Learner;
import com.example.vocabularymanagementsystem.services.LearnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LearnerControllerTest {

    @Mock
    LearnerService learnerService;

    @InjectMocks
    LearnerController learnerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllLearners() {
        List<Learner> learners = Arrays.asList(new Learner(), new Learner());
        when(learnerService.findAll()).thenReturn(learners);

        List<Learner> result = learnerController.getAllLearners();

        assertEquals(2, result.size());
        verify(learnerService, times(1)).findAll();
    }

    @Test
    void getLearnerById() {
        Learner learner = new Learner();
        learner.setLearnerId(1L);
        when(learnerService.findById(1L)).thenReturn(Optional.of(learner));

        Learner result = learnerController.getLearnerById(1L);

        assertEquals(learner, result);
        verify(learnerService, times(1)).findById(1L);
    }

    @Test
    void createLearner() {
        Learner learner = new Learner();
        when(learnerService.save(any(Learner.class))).thenReturn(learner);

        Learner result = learnerController.createLearner(new Learner());

        assertEquals(learner, result);
        verify(learnerService, times(1)).save(any(Learner.class));
    }

    @Test
    void updateLearner() {
        Learner existingLearner = new Learner();
        existingLearner.setLearnerId(1L);
        when(learnerService.findById(1L)).thenReturn(Optional.of(existingLearner));
        when(learnerService.save(any(Learner.class))).thenReturn(existingLearner);

        Learner result = learnerController.updateLearner(1L, new Learner());

        assertEquals(existingLearner, result);
        verify(learnerService, times(1)).findById(1L);
        verify(learnerService, times(1)).save(any(Learner.class));
    }

    @Test
    void deleteLearner() {
        ResponseEntity<Void> responseEntity = learnerController.deleteLearner(1L);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(learnerService, times(1)).deleteById(1L);
    }
}
