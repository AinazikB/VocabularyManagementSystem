package com.example.vocabularymanagementsystem.dto;

import com.example.vocabularymanagementsystem.entity.Learner;
import com.example.vocabularymanagementsystem.entity.Word;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReqRes {
    private int statusCode;
    private String error;
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private String username;
    private String email;
    private String role;
    private String password;
    private List<Word> words;
    private Learner learner;
}
