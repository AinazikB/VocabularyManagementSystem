package com.example.vocabularymanagementsystem.controllers;

import com.example.vocabularymanagementsystem.dto.ReqRes;
import com.example.vocabularymanagementsystem.entity.Word;
import com.example.vocabularymanagementsystem.repositories.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminUsers {
    @Autowired
    private WordRepository wordRepository;

    @GetMapping("/public/words")
    public ResponseEntity<Object> getAllWords(){
        return ResponseEntity.ok(wordRepository.findAll());
    }

    @PostMapping("/admin/savewords")
    public ResponseEntity<Object> signUp(@RequestBody ReqRes wordsRequest){
        Word wordsToSave = new Word();
        wordsToSave.setOriginalWord(wordsRequest.getUsername());
        return ResponseEntity.ok(wordRepository.save(wordsToSave));
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
