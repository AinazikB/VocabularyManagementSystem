package com.example.vocabularymanagementsystem.repositories;

import com.example.vocabularymanagementsystem.entity.Word;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@Transactional
class WordRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private WordRepository wordRepository;

    @Test
    void testGetAllWords() {
        Word word = new Word();
        word.setOriginalWord("testword");
        word.setTranslation("testtranslation");
        entityManager.persistAndFlush(word);

        List<Word> words = wordRepository.findAll();

        assertNotNull(words);
        assertEquals(1, words.size());
        Word savedWord = words.get(0);
        assertEquals("testword", savedWord.getOriginalWord());
        assertEquals("testtranslation", savedWord.getTranslation());
    }
    @AfterEach
    void tearDown() {
        entityManager.clear();
    }
}
