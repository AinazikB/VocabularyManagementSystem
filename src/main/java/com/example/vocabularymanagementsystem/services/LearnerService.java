package com.example.vocabularymanagementsystem.services;

import com.example.vocabularymanagementsystem.entity.Learner;
import com.example.vocabularymanagementsystem.entity.Word;
import com.example.vocabularymanagementsystem.repositories.LearnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class LearnerService implements UserDetailsService {

    @Autowired
    private LearnerRepository learnerRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Learner> learnerOptional = learnerRepository.findLearnerByUsername(username);


        Learner learner = learnerOptional.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));


        return new org.springframework.security.core.userdetails.User(
                learner.getEmail(),
                learner.getPassword(),
                true,
                true,
                true,
                true,
                getAuthorities(learner.getRole())
        );
    }

    private List<GrantedAuthority> getAuthorities(String role) {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role)); // Префикс "ROLE_" требуется Spring Security
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