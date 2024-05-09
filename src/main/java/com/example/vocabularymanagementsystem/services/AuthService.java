package com.example.vocabularymanagementsystem.services;

import com.example.vocabularymanagementsystem.dto.ReqRes;
import com.example.vocabularymanagementsystem.dto.WordDTO;
import com.example.vocabularymanagementsystem.entity.Learner;
import com.example.vocabularymanagementsystem.entity.Word;
import com.example.vocabularymanagementsystem.repositories.LearnerRepository;
import com.example.vocabularymanagementsystem.repositories.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthService {

    @Autowired
    private LearnerRepository learnerRepository;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private WordRepository wordRepository;
    public ReqRes signUp(ReqRes registrationRequest) {
        ReqRes resp = new ReqRes();
        try {
            boolean emailExists = learnerRepository.existsByEmail(registrationRequest.getEmail());
            if (emailExists) {
                resp.setMessage("User with this email already exists");
                resp.setStatusCode(400);
                return resp;
            }

            boolean usernameExists = learnerRepository.existsByUsername(registrationRequest.getUsername());
            if (usernameExists) {
                resp.setMessage("User with this username already exists");
                resp.setStatusCode(400);
                return resp;
            }

            Learner learner = new Learner();
            learner.setEmail(registrationRequest.getEmail());
            learner.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            learner.setRole(registrationRequest.getRole());
            learner.setUsername(registrationRequest.getUsername());

            Learner savedLearner = learnerRepository.save(learner);
            if (savedLearner != null && savedLearner.getLearnerId() > 0) {
                resp.setLearner(savedLearner);
                resp.setMessage("User saved successfully");
                resp.setStatusCode(200);
            }
        } catch (Exception e) {
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }
    public ReqRes signIn(ReqRes signInRequest){
        ReqRes response = new ReqRes();

        try {
           authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));
           var user = learnerRepository.findLearnerByUsername(signInRequest.getUsername()).orElseThrow();
           System.out.println("User is: " + user);
           var jwt = jwtUtils.generatedToken(user);
           var refreshToken = jwtUtils.generatedRefreshToken(new HashMap<>(), user);
           response.setStatusCode(200);
           response.setToken(jwt);
           response.setRefreshToken(refreshToken);
           response.setExpirationTime("24Hr");
           response.setMessage("Successfully signed in");
        }catch (Exception e){
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }
        return response;
    }

    public ReqRes refreshToken(ReqRes refreshTokenRequest){
        ReqRes response = new ReqRes();
        String username = jwtUtils.extractUsername(refreshTokenRequest.getToken());
        Learner learners =  learnerRepository.findLearnerByUsername(username).orElseThrow();
        if (jwtUtils.isTokenValid(refreshTokenRequest.getToken(), learners)){
            var jwt = jwtUtils.generatedToken(learners);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshTokenRequest.getToken());
            response.setExpirationTime("24Hr");
            response.setMessage("Successfully refreshed token");
        }
        response.setStatusCode(500);
        return response;
    }
    public ReqRes saveWord(ReqRes wordsRequest, String token) {
        ReqRes response = new ReqRes();
        try {
            String username = jwtUtils.extractUsername(token);

            Learner authenticatedUser = learnerRepository.findLearnerByUsername(username)
                    .orElseThrow(() -> new RuntimeException("Authenticated user not found"));

            Word wordToSave = new Word();
            wordToSave.setOriginalWord(wordsRequest.getOriginalWord());
            wordToSave.setTranslation(wordsRequest.getTranslation());
            wordToSave.setDifficultyLevel(wordsRequest.getDifficultyLevel());
            wordToSave.setStatus(wordsRequest.getStatus());

            wordToSave.setLearner(authenticatedUser);

            Word savedWord = wordRepository.save(wordToSave);

            if (savedWord != null && savedWord.getWordId() != null) {
                response.setMessage("Word saved successfully");
                response.setStatusCode(200);
            } else {
                response.setMessage("Failed to save word");
                response.setStatusCode(500);
            }
        } catch (Exception e) {
            response.setMessage("Exception occurred while saving word");
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }
        return response;
    }


}
