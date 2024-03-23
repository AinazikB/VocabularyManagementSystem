package com.example.vocabularymanagementsystem.mapper;

import com.example.vocabularymanagementsystem.dto.LearnerDTO;
import com.example.vocabularymanagementsystem.dto.WordDTO;
import com.example.vocabularymanagementsystem.entity.Learner;
import com.example.vocabularymanagementsystem.entity.Word;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LearnerMapperTest {

    private final LearnerMapper mapper = Mappers.getMapper(LearnerMapper.class);

    @Test
    void testLearnerToDto() {
        Learner learner = new Learner();
        learner.setLearnerId(1L);
        learner.setUsername("testUser");
        learner.setEmail("test@example.com");
        List<Word> words = new ArrayList<>();
        Word word = new Word();
        word.setWordId(1L);
        word.setOriginalWord("hello");
        word.setTranslation("hola");
        words.add(word);
        learner.setWords(words);

        LearnerDTO learnerDTO = mapper.learnerToDto(learner);

        assertEquals(learner.getLearnerId(), learnerDTO.getLearnerId());
        assertEquals(learner.getUsername(), learnerDTO.getUsername());
        assertEquals(learner.getEmail(), learnerDTO.getEmail());
        assertEquals(learner.getWords().size(), learnerDTO.getWords().size());
    }

    @Test
    void testDtoToLearner() {
        LearnerDTO learnerDTO = new LearnerDTO();
        learnerDTO.setLearnerId(1L);
        learnerDTO.setUsername("testUser");
        learnerDTO.setEmail("test@example.com");
        List<WordDTO> wordDTOs = new ArrayList<>();
        WordDTO wordDTO = new WordDTO();
        wordDTO.setWordId(1L);
        wordDTO.setOriginalWord("hello");
        wordDTO.setTranslation("hola");
        wordDTOs.add(wordDTO);
        learnerDTO.setWords(wordDTOs);

        Learner learner = mapper.dtoToLearner(learnerDTO);

        assertEquals(learnerDTO.getLearnerId(), learner.getLearnerId());
        assertEquals(learnerDTO.getUsername(), learner.getUsername());
        assertEquals(learnerDTO.getEmail(), learner.getEmail());
        assertEquals(learnerDTO.getWords().size(), learner.getWords().size());
    }
}
