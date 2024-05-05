package com.example.vocabularymanagementsystem.services;

import com.example.vocabularymanagementsystem.dto.ReqRes;
import com.example.vocabularymanagementsystem.entity.Learner;
import com.example.vocabularymanagementsystem.repositories.LearnerRepository;
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
    public ReqRes signUp(ReqRes registrationRequest){
        ReqRes resp = new ReqRes();
        try {
            Learner learner = new Learner();
            learner.setEmail(registrationRequest.getEmail());
            learner.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            learner.setRole(registrationRequest.getRole());
            Learner learner1 = learnerRepository.save(learner);
            if (learner1 != null && learner1.getLearnerId()>0){
                resp.setLearner(learner1);
                resp.setMessage("User saved successfully");
                resp.setStatusCode(200);
            }
        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }

    public ReqRes signIn(ReqRes signInRequest){
        ReqRes response = new ReqRes();

        try {
           authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));
           var user = learnerRepository.findByUsername(signInRequest.getUsername()).orElseThrow();
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
        String ourUsername = jwtUtils.extractUsername(refreshTokenRequest.getToken());
        Learner learners =  learnerRepository.findByUsername(ourUsername).orElseThrow();
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
}
