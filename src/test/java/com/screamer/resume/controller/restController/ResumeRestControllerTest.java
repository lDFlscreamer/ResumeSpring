package com.screamer.resume.controller.restController;

import com.screamer.resume.config.JwtDecoderTestConfig;
import com.screamer.resume.entity.Resume;
import com.screamer.resume.exceptions.resume.FileCorruptedException;
import com.screamer.resume.exceptions.resume.ResumeNotFoundException;
import com.screamer.resume.service.businessServices.resume.ResumeService;
import com.screamer.resume.utils.JsonConverter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Import({JwtDecoderTestConfig.class, JsonConverter.class})
@AutoConfigureMockMvc
class ResumeRestControllerTest {
    @Autowired
    private JsonConverter jsonConverter;

    @MockBean
    private ResumeService resumeService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getResume() throws Exception {
        String resumeId = UUID.randomUUID().toString();
        Resume mockResume = new Resume();

        when(resumeService.getResume(resumeId)).thenReturn(mockResume);

        MvcResult result = this.mockMvc
                .perform(
                        get("/resume/".concat(resumeId))
                                .header(HttpHeaders.AUTHORIZATION, "Bearer token"))
                .andExpect(status().isAccepted())
                .andDo(print())
                .andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        Resume resume = jsonConverter.convertJsonToObject(contentAsString, Resume.class);

        assertEquals(mockResume.get_id(), resume.get_id(), "Resume id do not match");
    }

    @Test
    void getResume_withOutJwt() throws Exception {
        String resumeId = UUID.randomUUID().toString();
        Resume mockResume = new Resume();

        when(resumeService.getResume(resumeId)).thenReturn(mockResume);

        MvcResult result = this.mockMvc
                .perform(get("/resume/".concat(resumeId)))
                .andExpect(status().isAccepted())
                .andDo(print())
                .andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        Resume resume = jsonConverter.convertJsonToObject(contentAsString, Resume.class);

        assertEquals(mockResume.get_id(), resume.get_id(), "Resume id do not match");
    }

    @Test
    void getResume_withoutResume() throws Exception {
        String resumeId = UUID.randomUUID().toString();

        when(resumeService.getResume(resumeId)).thenThrow(new ResumeNotFoundException(resumeId));

        this.mockMvc
                .perform(
                        get("/resume/".concat(resumeId))
                                .header(HttpHeaders.AUTHORIZATION, "Bearer token"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void updateResume() throws Exception {
        String resumeId = UUID.randomUUID().toString();
        String position = "testPosition";
        String fileName = "fileName";
        MockMultipartFile file = new MockMultipartFile(
                "file",
                fileName,
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );
        Resume mockResume = new Resume();
        mockResume.set_id(resumeId);
        mockResume.setPosition(position);
        mockResume.setResumeFileName(fileName);

        when(resumeService.updateResume(resumeId, position, file)).thenReturn(mockResume);

        MvcResult result = this.mockMvc
                .perform(
                        multipart("/resume/".concat(resumeId))
                                .file(file)
                                .header(HttpHeaders.AUTHORIZATION, "Bearer token")
                                .param("position", position)
                )
                .andExpect(status().isAccepted())
                .andDo(print())
                .andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        Resume resume = jsonConverter.convertJsonToObject(contentAsString, Resume.class);

        assertEquals(mockResume.get_id(), resume.get_id(), "Resume id do not match");
        assertEquals(mockResume.getPosition(), resume.getPosition(), "Resume position do not match");
        assertEquals(mockResume.getResumeFileName(), resume.getResumeFileName(), "Resume fileName do not match");
    }

    @Test
    void updateResume_withoutJwt() throws Exception {
        String resumeId = UUID.randomUUID().toString();
        String position = UUID.randomUUID().toString();
        String fileName = "fileName";
        MockMultipartFile file = new MockMultipartFile(
                "file",
                fileName,
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );


        this.mockMvc
                .perform(
                        multipart("/resume/".concat(resumeId))
                                .file(file)
                                .param("position", position)
                )
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    void updateResume_withIoException() throws Exception {
        String resumeId = UUID.randomUUID().toString();
        String position = "testPosition";
        String fileName = "fileName";
        MockMultipartFile file = new MockMultipartFile(
                "file",
                fileName,
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );
        Resume mockResume = new Resume();
        mockResume.set_id(resumeId);
        mockResume.setPosition(position);
        mockResume.setResumeFileName(fileName);

        when(resumeService.updateResume(resumeId, position, file)).thenThrow(new FileCorruptedException(file, new IOException()));

        this.mockMvc
                .perform(
                        multipart("/resume/".concat(resumeId))
                                .file(file)
                                .header(HttpHeaders.AUTHORIZATION, "Bearer token")
                                .param("position", position)
                )
                .andExpect(status().isNotAcceptable())
                .andDo(print());
    }
}