package com.screamer.resume.controller.restController;

import com.screamer.resume.entity.Resume;
import com.screamer.resume.entity.User;
import com.screamer.resume.exceptions.resume.FileCorruptedException;
import com.screamer.resume.service.businessServices.resume.UserResumeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith({SpringExtension.class})
class UserResumeRestControllerTest {
    @Mock
    private UserResumeService userResumeService;

    @InjectMocks
    private UserResumeRestController controller;

    @Test
    void getAllUserResume() {
        List<Resume> resumeList = new ArrayList<>();
        resumeList.add(new Resume());
        resumeList.add(new Resume());
        Authentication mockAuth = mock(Authentication.class);

        when(userResumeService.getAllResume(mockAuth)).thenReturn(resumeList);

        List<Resume> allUserResume = controller.getAllUserResume(mockAuth);

        verify(userResumeService, times(1)).getAllResume(mockAuth);
        assertEquals(resumeList.size(), allUserResume.size(), "resume list do not match");
    }

    @Test
    void uploadResume() throws FileCorruptedException {
        Authentication mockAuth = mock(Authentication.class);
        String position = "testPosition";
        String fileName = "fileName";
        MockMultipartFile file = new MockMultipartFile(
                "file",
                fileName,
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );
        User mockUser = mock(User.class);

        when(userResumeService.addResumeToUser(mockAuth, position, file)).thenReturn(mockUser);

        User user = controller.uploadResume(mockAuth, position, file);

        verify(userResumeService, times(1)).addResumeToUser(mockAuth, position, file);
        assertEquals(mockUser, user, "User do not match");
    }

    @Test
    void uploadResume_FileCorrupted() throws FileCorruptedException {
        Authentication mockAuth = mock(Authentication.class);
        String position = "testPosition";
        String fileName = "fileName";
        MockMultipartFile file = new MockMultipartFile(
                "file",
                fileName,
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );
        FileCorruptedException fileCorruptedException = new FileCorruptedException(file, new IOException());

        when(userResumeService.addResumeToUser(mockAuth, position, file)).thenThrow(fileCorruptedException);

        Executable executable = () -> controller.uploadResume(mockAuth, position, file);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, executable);

        verify(userResumeService, times(1)).addResumeToUser(mockAuth, position, file);
        assertEquals(HttpStatus.NOT_ACCEPTABLE, exception.getStatus(), "Response status do not match");
        assertEquals(fileCorruptedException, exception.getCause(), "Cause do not match");
    }

    @Test
    void deleteResumeFromUser() {
        String resumeId = UUID.randomUUID().toString();
        User mockUser = mock(User.class);
        Authentication mockAuth = mock(Authentication.class);

        when(userResumeService.removeResumeFromUser(mockAuth, resumeId))
                .thenReturn(mockUser);

        User user = controller.deleteResumeFromUser(mockAuth, resumeId);

        verify(userResumeService, times(1)).removeResumeFromUser(any(Authentication.class), eq(resumeId));
        verify(userResumeService, times(1)).removeResumeFromUser(mockAuth, resumeId);
        assertEquals(mockUser.get_id(), user.get_id(), "user id do not match");
    }
}