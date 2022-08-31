package com.screamer.resume.service.dbServices.user;

import com.screamer.resume.entity.User;
import com.screamer.resume.exceptions.user.UserNotFoundException;
import com.screamer.resume.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith({SpringExtension.class})
class UserDbServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserDbServiceImpl userDbService;

    @Test
    void getUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(mock(User.class));

        when(userRepository.findAll()).thenReturn(userList);

        List<User> users = userDbService.getUsers();

        verify(userRepository, times(1)).findAll();
        assertEquals(userList, users, "User list are different");
    }

    @Test
    void getOrCreate() {
        User mockUser = mock(User.class);
        String userAuthId = "userAuthId";

        when(userRepository.findByUserAuthId(userAuthId)).thenReturn(Optional.of(mockUser));

        User user = userDbService.getOrCreate(userAuthId);

        verify(userRepository, times(1)).findByUserAuthId(userAuthId);
        verify(userRepository, times(0)).save(any(User.class));
        assertEquals(mockUser, user, "Users do not match");
    }

    @Test
    void getOrCreate_withoutUser() {
        String userAuthId = "userAuthId";

        when(userRepository.findByUserAuthId(userAuthId)).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenAnswer(u -> u.getArguments()[0]);

        User user = userDbService.getOrCreate(userAuthId);

        verify(userRepository, times(1)).findByUserAuthId(userAuthId);
        verify(userRepository, times(1)).save(any(User.class));
        assertEquals(userAuthId, user.getUserAuthId(), "Users do not match");
    }

    @Test
    void getUserById() throws UserNotFoundException {
        String userId = UUID.randomUUID().toString();
        User mockUser = mock(User.class);

        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));

        User user = userDbService.getUserById(userId);

        verify(userRepository, times(1)).findById(userId);
        assertEquals(mockUser, user, "Users do not match");
    }

    @Test
    void getUserById_UserNotFound() {
        String userId = UUID.randomUUID().toString();

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Executable executable = () -> userDbService.getUserById(userId);
        UserNotFoundException exception = assertThrows(UserNotFoundException.class, executable);

        verify(userRepository, times(1)).findById(userId);
        assertEquals(userId, exception.getUserId(), "Users id do not match");
    }

    @Test
    void getUserByUserAuthId() throws UserNotFoundException {
        User mockUser = mock(User.class);
        String userAuthId = "userAuthId";

        when(userRepository.findByUserAuthId(userAuthId)).thenReturn(Optional.of(mockUser));

        User user = userDbService.getUserByUserAuthId(userAuthId);

        verify(userRepository, times(1)).findByUserAuthId(userAuthId);
        assertEquals(mockUser, user, "Users do not match");
    }

    @Test
    void getUserByUserAuthId_UserNotFound() {
        String userAuthId = "userAuthId";

        when(userRepository.findByUserAuthId(userAuthId)).thenReturn(Optional.empty());

        Executable executable = () -> userDbService.getUserByUserAuthId(userAuthId);
        UserNotFoundException exception = assertThrows(UserNotFoundException.class, executable);

        verify(userRepository, times(1)).findByUserAuthId(userAuthId);
        assertEquals(userAuthId, exception.getUserId(), "Users id do not match");
    }

    @Test
    void updateUser() {
        User mockUser = mock(User.class);

        when(userRepository.save(mockUser)).thenReturn(mockUser);

        User user = userDbService.updateUser(mockUser);

        verify(userRepository, times(1)).save(any(User.class));
        assertEquals(mockUser, user, "Users do not match");
    }
}