package com.example.vocabularymanagementsystem.repositories;

import com.example.vocabularymanagementsystem.entity.Learner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@Transactional
class LearnerRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private LearnerRepository learnerRepository;

    @Test
    void testGetAllLearners() {

        Learner learner = new Learner();
        learner.setUsername("testuser");
        learner.setEmail("test@example.com");
        entityManager.persistAndFlush(learner);

        List<Learner> learners = learnerRepository.findAll();

        assertNotNull(learners);
        assertEquals(1, learners.size());
        Learner savedLearner = learners.get(0);
        assertEquals("testuser", savedLearner.getUsername());
        assertEquals("test@example.com", savedLearner.getEmail());
    }
    @AfterEach
    void tearDown() {
        entityManager.clear();
    }
}
