package com.screamer.resume.config.listeners;

import com.screamer.resume.entity.Resume;
import com.screamer.resume.entity.User;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith({SpringExtension.class})
class UserCascadeMongoEventListenerTest {

    @Mock
    private MongoOperations mongoOperations;

    @InjectMocks
    private UserCascadeMongoEventListener userEventListener;

    @Test
    void onBeforeConvert() {
        BeforeConvertEvent<User> event = mock(BeforeConvertEvent.class);
        User mockUser = mock(User.class);
        List<Resume> resumeList = new ArrayList<>();
        resumeList.add(mock(Resume.class));
        resumeList.add(mock(Resume.class));

        when(event.getSource()).thenReturn(mockUser);
        when(mockUser.getResumeList()).thenReturn(resumeList);
        when(mongoOperations.save(any())).thenAnswer(o -> o.getArguments()[0]);

        userEventListener.onBeforeConvert(event);

        verify(mockUser, atLeastOnce()).getResumeList();
        verify(mongoOperations, times(resumeList.size())).save(any(Resume.class));
    }

    @Test
    void onBeforeConvert_withEmptyResumeList() {
        BeforeConvertEvent<User> event = mock(BeforeConvertEvent.class);
        User mockUser = mock(User.class);
        List<Resume> resumeList = new ArrayList<>();

        when(event.getSource()).thenReturn(mockUser);
        when(mockUser.getResumeList()).thenReturn(resumeList);
        when(mongoOperations.save(any())).thenAnswer(o -> o.getArguments()[0]);

        userEventListener.onBeforeConvert(event);

        verify(mockUser, atLeastOnce()).getResumeList();
        verify(mongoOperations, never()).save(any(Resume.class));
    }

    @Test
    void onBeforeConvert_withoutResumeList() {
        BeforeConvertEvent<User> event = mock(BeforeConvertEvent.class);
        User mockUser = mock(User.class);

        when(event.getSource()).thenReturn(mockUser);
        when(mockUser.getResumeList()).thenReturn(null);
        when(mongoOperations.save(any())).thenAnswer(o -> o.getArguments()[0]);

        userEventListener.onBeforeConvert(event);

        verify(mockUser, atLeastOnce()).getResumeList();
        verify(mongoOperations, never()).save(any(Resume.class));
    }

    @Test
    void onBeforeDelete() {
        BeforeDeleteEvent<User> event = mock(BeforeDeleteEvent.class);
        Document mockDocument = mock(Document.class);
        Object documentId = mock(Object.class);

        User mockUser = mock(User.class);
        List<Resume> resumeList = new ArrayList<>();
        resumeList.add(mock(Resume.class));
        resumeList.add(mock(Resume.class));


        when(event.getSource()).thenReturn(mockDocument);
        when(mockDocument.get("_id")).thenReturn(documentId);
        when(mongoOperations.findById(documentId, User.class)).thenReturn(mockUser);
        when(mockUser.getResumeList()).thenReturn(resumeList);

        userEventListener.onBeforeDelete(event);

        verify(mongoOperations, times(1)).findById(documentId, User.class);
        verify(mockUser, atLeastOnce()).getResumeList();
        verify(mongoOperations, times(resumeList.size())).remove(any(Resume.class));
    }

    @Test
    void onBeforeDelete_withoutUser() {
        BeforeDeleteEvent<User> event = mock(BeforeDeleteEvent.class);
        Document mockDocument = mock(Document.class);
        Object documentId = mock(Object.class);

        when(event.getSource()).thenReturn(mockDocument);
        when(mockDocument.get("_id")).thenReturn(documentId);
        when(mongoOperations.findById(documentId, User.class)).thenReturn(null);

        userEventListener.onBeforeDelete(event);

        verify(mongoOperations, times(1)).findById(documentId, User.class);
        verify(mongoOperations, never()).remove(any(Resume.class));
    }

    @Test
    void onBeforeDelete_withEmptyResumeList() {
        BeforeDeleteEvent<User> event = mock(BeforeDeleteEvent.class);
        Document mockDocument = mock(Document.class);
        Object documentId = mock(Object.class);

        User mockUser = mock(User.class);
        List<Resume> resumeList = new ArrayList<>();


        when(event.getSource()).thenReturn(mockDocument);
        when(mockDocument.get("_id")).thenReturn(documentId);
        when(mongoOperations.findById(documentId, User.class)).thenReturn(mockUser);
        when(mockUser.getResumeList()).thenReturn(resumeList);

        userEventListener.onBeforeDelete(event);

        verify(mongoOperations, times(1)).findById(documentId, User.class);
        verify(mockUser, atLeastOnce()).getResumeList();
        verify(mongoOperations, never()).remove(any(Resume.class));
    }
}