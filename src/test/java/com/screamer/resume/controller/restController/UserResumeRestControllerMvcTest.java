package com.screamer.resume.controller.restController;

import com.screamer.resume.config.JwtDecoderTestConfig;
import com.screamer.resume.entity.Resume;
import com.screamer.resume.entity.User;
import com.screamer.resume.exceptions.resume.FileCorruptedException;
import com.screamer.resume.service.businessServices.resume.UserResumeService;
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
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Import({JwtDecoderTestConfig.class, JsonConverter.class})
@AutoConfigureMockMvc
class UserResumeRestControllerMvcTest {
    @Autowired
    private JsonConverter jsonConverter;

    @MockBean
    private UserResumeService userResumeService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllUserResume() throws Exception {
        List<Resume> resumeList = new ArrayList<>();
        resumeList.add(new Resume());
        resumeList.add(new Resume());

        when(userResumeService.getAllResume(any(Authentication.class))).thenReturn(resumeList);

        MvcResult result = this.mockMvc
                .perform(
                        get("/user/resume")
                                .header(HttpHeaders.AUTHORIZATION, "Bearer token"))
                .andExpect(status().isAccepted())
                .andDo(print())
                .andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        List<Resume> messages = jsonConverter.convertJsonToListOfObject(contentAsString, Resume.class);

        assertEquals(resumeList.size(), messages.size(), "resume list do not match");
    }

    @Test
    void getAllUserResume_withoutJwt() throws Exception {
        List<Resume> resumeList = new ArrayList<>();
        resumeList.add(new Resume());
        resumeList.add(new Resume());

        when(userResumeService.getAllResume(any(Authentication.class))).thenReturn(resumeList);

        this.mockMvc
                .perform(
                        get("/user/resume"))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    void uploadResume() throws Exception {
        User mockUser = new User();

        String position = "testPosition";
        String fileName = "fileName";
        MockMultipartFile file = new MockMultipartFile(
                "file",
                fileName,
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );

        when(userResumeService.addResumeToUser(any(Authentication.class), eq(position), eq(file)))
                .thenReturn(mockUser);

        MvcResult result = this.mockMvc
                .perform(
                        multipart("/user/resume/uploadResume")
                                .file(file)
                                .header(HttpHeaders.AUTHORIZATION, "Bearer token")
                                .param("position", position)
                )
                .andExpect(status().isAccepted())
                .andDo(print())
                .andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        User user = jsonConverter.convertJsonToObject(contentAsString, User.class);

        assertEquals(mockUser.get_id(), user.get_id(), "User id do not match");
    }

    @Test
    void uploadResume_withoutJwt() throws Exception {
        String position = "testPosition";
        String fileName = "fileName";
        MockMultipartFile file = new MockMultipartFile(
                "file",
                fileName,
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );


        this.mockMvc
                .perform(
                        multipart("/user/resume/uploadResume")
                                .file(file)
                                .param("position", position)
                )
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    void uploadResume_withFileCorrupted() throws Exception {
        String position = "testPosition";
        String fileName = "fileName";
        MockMultipartFile file = new MockMultipartFile(
                "file",
                fileName,
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );

        when(userResumeService.addResumeToUser(any(Authentication.class), eq(position), eq(file)))
                .thenThrow(new FileCorruptedException(file, new IOException()));

        this.mockMvc
                .perform(
                        multipart("/user/resume/uploadResume")
                                .file(file)
                                .header(HttpHeaders.AUTHORIZATION, "Bearer token")
                                .param("position", position)
                )
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void deleteResumeFromUser() throws Exception {
        String resumeId = UUID.randomUUID().toString();
        User mockUser = new User();

        when(userResumeService.removeResumeFromUser(any(Authentication.class), eq(resumeId)))
                .thenReturn(mockUser);

        MvcResult result = this.mockMvc
                .perform(
                        delete("/user/resume/".concat(resumeId))
                                .header(HttpHeaders.AUTHORIZATION, "Bearer token"))
                .andExpect(status().isNoContent())
                .andDo(print())
                .andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        User user = jsonConverter.convertJsonToObject(contentAsString, User.class);

        assertEquals(mockUser.get_id(), user.get_id(), "user id do not match");
        verify(userResumeService, times(1))
                .removeResumeFromUser(any(Authentication.class), resumeId);
    }

    @Test
    void deleteResumeFromUser_withoutJwt() throws Exception {
        String resumeId = UUID.randomUUID().toString();

        this.mockMvc
                .perform(
                        delete("/user/resume/".concat(resumeId)))
                .andExpect(status().isUnauthorized())
                .andDo(print());

        verify(userResumeService, never())
                .removeResumeFromUser(any(Authentication.class), anyString());
    }
}