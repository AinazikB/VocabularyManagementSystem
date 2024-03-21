package com.example.vocabularymanagementsystem.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class ExceptionHandlerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void handleNotFoundException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/non-existing-url"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
