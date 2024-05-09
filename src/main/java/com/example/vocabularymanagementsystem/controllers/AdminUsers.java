package com.example.vocabularymanagementsystem.controllers;

import com.example.vocabularymanagementsystem.dto.ReqRes;
import com.example.vocabularymanagementsystem.entity.Word;
import com.example.vocabularymanagementsystem.enums.WordStatus;
import com.example.vocabularymanagementsystem.repositories.WordRepository;
import com.example.vocabularymanagementsystem.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminUsers {
    @Autowired
    private WordRepository wordRepository;
    @Autowired
    private AuthService authService;
    @GetMapping("/public/words")
    public ResponseEntity<Object> getAllWords(){
        return ResponseEntity.ok(wordRepository.findAll());
    }

    @PostMapping("/admin/savewords")
    public ResponseEntity<Object> saveWords(@RequestBody ReqRes wordsRequest,
                                            @RequestHeader("Authorization") String token) {
        token = token.substring(7);

        ReqRes response = authService.saveWord(wordsRequest, token);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/alone")
    public ResponseEntity<Object> userAlong(){
        return ResponseEntity.ok("Users alone can access this API only");
    }
    @GetMapping("/adminuser/both")
    public ResponseEntity<Object> bothUsersAndUSersApi(){
        return ResponseEntity.ok("Both admin and users can access the API");
    }
}
