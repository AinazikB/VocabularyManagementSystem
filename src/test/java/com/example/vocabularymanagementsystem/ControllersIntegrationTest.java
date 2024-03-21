package com.example.vocabularymanagementsystem;

import com.example.vocabularymanagementsystem.entity.Learner;
import com.example.vocabularymanagementsystem.entity.Word;
import com.example.vocabularymanagementsystem.services.LearnerService;
import com.example.vocabularymanagementsystem.services.WordService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllersIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LearnerService learnerService;

    @MockBean
    private WordService wordService;

    @Test
    public void testGetAllLearners() throws Exception {
        Learner learner1 = new Learner();
        learner1.setLearnerId(1L);
        Learner learner2 = new Learner();
        learner2.setLearnerId(2L);
        when(learnerService.findAll()).thenReturn(Arrays.asList(learner1, learner2));

        mockMvc.perform(MockMvcRequestBuilders.get("/learners")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].learnerId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].learnerId").value(2));
    }

    @Test
    public void testGetLearnerById() throws Exception {
        Learner learner = new Learner();
        learner.setLearnerId(1L);
        learner.setUsername("testUser");
        when(learnerService.findById(1L)).thenReturn(Optional.of(learner));

        mockMvc.perform(MockMvcRequestBuilders.get("/learners/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.learnerId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("testUser"));
    }

    @Test
    public void testGetAllWords() throws Exception {
        Word word1 = new Word();
        word1.setWordId(1L);
        Word word2 = new Word();
        word2.setWordId(2L);
        when(wordService.getAllWords()).thenReturn(Arrays.asList(word1, word2));

        mockMvc.perform(MockMvcRequestBuilders.get("/words")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].wordId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].wordId").value(2));
    }

}
