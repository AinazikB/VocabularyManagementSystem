package com.example.vocabularymanagementsystem.controllers;

import com.example.vocabularymanagementsystem.entity.Learner;
import com.example.vocabularymanagementsystem.services.LearnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/learners")
@Api(tags = "Learner Management", description = "Endpoints for managing learners")
public class LearnerController {

    private final LearnerService learnerService;

    @Autowired
    public LearnerController(LearnerService learnerService) {
        this.learnerService = learnerService;
    }

    @GetMapping
    public List<Learner> getAllLearners() {
        return learnerService.findAll();
    }

    @GetMapping("/{id}")
    public Learner getLearnerById(@PathVariable Long id) {
        Optional<Learner> learnerOptional = learnerService.findById(id);
        return learnerOptional.orElseThrow(() -> new RuntimeException("Learner not found with id: " + id));
    }

   @PostMapping
    public Learner createLearner(@RequestBody Learner learner) {
     return learnerService.save(learner);
   }

    @PutMapping("/{id}")
    public Learner updateLearner(@PathVariable Long id,
                                 @RequestBody Learner learner) {
        Optional<Learner> existingLearnerOptional = learnerService.findById(id);
        Learner existingLearner = existingLearnerOptional.orElseThrow(() -> new RuntimeException("Learner not found with id: " + id));

        existingLearner.setUsername(learner.getUsername());
        existingLearner.setEmail(learner.getEmail());
        existingLearner.setPassword(learner.getPassword());

        return learnerService.save(existingLearner);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLearner(@PathVariable Long id) {
        learnerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
