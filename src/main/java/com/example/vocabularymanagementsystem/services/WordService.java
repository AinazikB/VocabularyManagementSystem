package com.example.vocabularymanagementsystem.services;

import com.example.vocabularymanagementsystem.entity.Word;
import com.example.vocabularymanagementsystem.repositories.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WordService {

    @Autowired
    private WordRepository wordRepository;

    public List<Word> getAllWords() {
        return wordRepository.findAll();
    }

    public Word getWordById(Long wordId) {
        return wordRepository.findById(wordId)
                .orElseThrow(() -> new RuntimeException("Word not found"));
    }

    public Word createWord(Word word) {
        return wordRepository.save(word);
    }

    public Word updateWord(Long wordId, Word wordDetails) {
        Word word = wordRepository.findById(wordId)
                .orElseThrow(() -> new RuntimeException("Word not found"));

        word.setOriginalWord(wordDetails.getOriginalWord());
        word.setTranslation(wordDetails.getTranslation());
        word.setDifficultyLevel(wordDetails.getDifficultyLevel());

        return wordRepository.save(word);
    }

    public void deleteWord(Long wordId) {
        wordRepository.deleteById(wordId);
    }
}
