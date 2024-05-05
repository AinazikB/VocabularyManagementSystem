package com.example.vocabularymanagementsystem.controllers;

import com.example.vocabularymanagementsystem.entity.Learner;
import com.example.vocabularymanagementsystem.services.LearnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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
    @ApiOperation(value = "Get all learners", response = List.class)
    public List<Learner> getAllLearners() {
        return learnerService.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a learner by ID", response = Learner.class)
    public Learner getLearnerById(@PathVariable @ApiParam(value = "Learner ID", example = "1") Long id) {
        Optional<Learner> learnerOptional = learnerService.findById(id);
        return learnerOptional.orElseThrow(() -> new RuntimeException("Learner not found with id: " + id));
    }

    @PostMapping
    @ApiOperation(value = "Create a new learner", response = Learner.class)
    public Learner createLearner(@RequestBody @ApiParam(value = "New learner details") Learner learner) {
        return learnerService.save(learner);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update an existing learner", response = Learner.class)
    public Learner updateLearner(@PathVariable @ApiParam(value = "Learner ID", example = "1") Long id,
                                 @RequestBody @ApiParam(value = "Updated learner details") Learner learner) {
        Optional<Learner> existingLearnerOptional = learnerService.findById(id);
        Learner existingLearner = existingLearnerOptional.orElseThrow(() -> new RuntimeException("Learner not found with id: " + id));

        existingLearner.setUsername(learner.getUsername());
        existingLearner.setEmail(learner.getEmail());
        existingLearner.setPassword(learner.getPassword());

        return learnerService.save(existingLearner);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a learner by ID")
    public ResponseEntity<Void> deleteLearner(@PathVariable @ApiParam(value = "Learner ID", example = "1") Long id) {
        learnerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
