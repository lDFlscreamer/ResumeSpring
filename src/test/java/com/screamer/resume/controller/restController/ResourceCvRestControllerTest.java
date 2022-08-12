package com.screamer.resume.controller.restController;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ResourceCvRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Java Cv endpoint Test")
    void getJavaCv() throws Exception {
        this.mockMvc
                .perform(get("/resource/CV/Java"))
                .andExpect(status().isOk());
    }
}