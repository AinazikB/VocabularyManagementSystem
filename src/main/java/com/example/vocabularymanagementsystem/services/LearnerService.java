package com.example.vocabularymanagementsystem.services;

import com.example.vocabularymanagementsystem.entity.Learner;
import com.example.vocabularymanagementsystem.entity.Word;
import com.example.vocabularymanagementsystem.repositories.LearnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LearnerService implements UserDetailsService {

    @Autowired
    private LearnerRepository learnerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Learner> learnerOptional = learnerRepository.findByUsername(username);
        return learnerOptional.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

   /* @Autowired
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
    public Learner processNestedJson(Learner inputLearner) {
        Learner savedLearner = learnerRepository.save(inputLearner);
        for (Word word : savedLearner.getWords()) {
            if (word.getLearner() != null) {
                Learner nestedLearner = processNestedJson(word.getLearner());
                word.setLearner(nestedLearner)
            }
        }
        return savedLearner;
    }*/

}