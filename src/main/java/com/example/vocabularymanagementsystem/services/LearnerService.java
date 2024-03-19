package com.example.vocabularymanagementsystem.services;

import com.example.vocabularymanagementsystem.entity.Learner;
import com.example.vocabularymanagementsystem.repositories.LearnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LearnerService {

    private final LearnerRepository learnerRepository;

    @Autowired
    public LearnerService(LearnerRepository learnerRepository) {
        this.learnerRepository = learnerRepository;
    }

    public List<Learner> findAll() {
        return learnerRepository.findAll();
    }

    public Optional<Learner> findById(Long id) {
        return learnerRepository.findById(id);
    }

    public Learner save(Learner learner) {
        return learnerRepository.save(learner);
    }

    public void deleteById(Long id) {
        learnerRepository.deleteById(id);
    }
}