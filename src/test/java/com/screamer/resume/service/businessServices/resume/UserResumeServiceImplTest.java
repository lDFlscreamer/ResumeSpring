package com.screamer.resume.service.businessServices.resume;

import com.screamer.resume.entity.Resume;
import com.screamer.resume.entity.User;
import com.screamer.resume.exceptions.resume.FileCorruptedException;
import com.screamer.resume.service.dbServices.resume.ResumeDbServiceImpl;
import com.screamer.resume.service.dbServices.user.UserDbService;
import com.screamer.resume.utils.ResumeFabric;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith({SpringExtension.class})
class UserResumeServiceImplTest {
    @Mock
    UserDbService userDbService;
    @Mock
    ResumeFabric resumeFabric;
    @Mock
    ResumeDbServiceImpl resumeDbService;

    @InjectMocks
    UserResumeServiceImpl userResumeService;

    @Test
    void getAllResume() {
        Authentication auth = mock(Authentication.class);
        String authName = "authName";
        User mockUser = mock(User.class);
        List<Resume> resumeList = new ArrayList<>();
        resumeList.add(mock(Resume.class));

        when(auth.getName()).thenReturn(authName);
        when(userDbService.getOrCreate(authName)).thenReturn(mockUser);
        when(mockUser.getResumeList()).thenReturn(resumeList);

        List<Resume> allResume = userResumeService.getAllResume(auth);

        verify(userDbService, times(1)).getOrCreate(authName);
        assertEquals(allResume, resumeList, "received resume are different");
    }

    @Test
    void addResumeToUser() throws FileCorruptedException, IOException {
        Authentication auth = mock(Authentication.class);
        String authName = "authName";
        User mockUser = new User();
        String position = "any";
        MultipartFile mockFile = mock(MultipartFile.class);
        Resume mockResume = mock(Resume.class);


        when(auth.getName()).thenReturn(authName);
        when(userDbService.getOrCreate(authName)).thenReturn(mockUser);
        when(resumeFabric.buildResume(position, mockFile)).thenReturn(mockResume);
        when(userDbService.updateUser(mockUser)).thenReturn(mockUser);

        User user = userResumeService.addResumeToUser(auth, position, mockFile);

        verify(userDbService, times(1)).getOrCreate(authName);
        verify(userDbService, times(1)).updateUser(mockUser);
        verify(resumeFabric, times(1)).buildResume(position, mockFile);
        assertEquals(mockUser, user, "User are changed");
        assertTrue(user.getResumeList().contains(mockResume), "Resume arent added");
    }

    @Test
    void addResumeToUser_FileCorrupted() throws IOException {
        Authentication auth = mock(Authentication.class);
        String authName = "authName";
        User mockUser = new User();
        String position = "any";
        MultipartFile mockFile = mock(MultipartFile.class);


        when(auth.getName()).thenReturn(authName);
        when(userDbService.getOrCreate(authName)).thenReturn(mockUser);
        when(resumeFabric.buildResume(position, mockFile)).thenThrow(new IOException());

        Executable executable = () -> userResumeService.addResumeToUser(auth, position, mockFile);
        FileCorruptedException exception = assertThrows(FileCorruptedException.class, executable);

        verify(userDbService, times(1)).getOrCreate(authName);
        verify(resumeFabric, times(1)).buildResume(position, mockFile);
        verify(userDbService, times(0)).updateUser(mockUser);
        assertEquals(mockFile, exception.getCorruptedFile());
    }

    @Test
    void removeResumeFromUser() {
        Authentication auth = mock(Authentication.class);
        String authName = "authName";

        Resume mockResume = new Resume();
        String resumeId = UUID.randomUUID().toString();
        mockResume.set_id(resumeId);

        User mockUser = new User();
        List<Resume> resumeList = new ArrayList<>();
        resumeList.add(mockResume);
        mockUser.setResumeList(resumeList);

        when(auth.getName()).thenReturn(authName);
        when(userDbService.getOrCreate(authName)).thenReturn(mockUser);
        when(userDbService.updateUser(mockUser)).thenReturn(mockUser);

        User user = userResumeService.removeResumeFromUser(auth, resumeId);

        verify(userDbService, times(1)).getOrCreate(authName);
        verify(resumeDbService, times(1)).deleteResume(resumeId);
        verify(userDbService, times(1)).updateUser(mockUser);
        assertFalse(user.getResumeList().contains(mockResume), "resume still in users resume list");
    }

    @Test
    void removeResumeFromUser_withoutResume() {
        Authentication auth = mock(Authentication.class);
        String authName = "authName";

        Resume mockResume = new Resume();
        String resumeId = UUID.randomUUID().toString();
        mockResume.set_id(resumeId);

        User mockUser = new User();
        List<Resume> resumeList = new ArrayList<>();
        mockUser.setResumeList(resumeList);

        when(auth.getName()).thenReturn(authName);
        when(userDbService.getOrCreate(authName)).thenReturn(mockUser);
        when(userDbService.updateUser(mockUser)).thenReturn(mockUser);

        User user = userResumeService.removeResumeFromUser(auth, resumeId);

        verify(userDbService, times(1)).getOrCreate(authName);
        verify(resumeDbService, times(0)).deleteResume(resumeId);
        verify(userDbService, times(1)).updateUser(mockUser);
        assertFalse(user.getResumeList().contains(mockResume), "resume still in users resume list");
    }
}