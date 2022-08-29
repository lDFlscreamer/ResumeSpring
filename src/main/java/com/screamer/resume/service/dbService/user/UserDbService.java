package com.screamer.resume.service.dbService.user;

import com.screamer.resume.entity.User;
import com.screamer.resume.exceptions.user.UserNotFoundException;

import java.util.List;

public interface UserDbService {
    List<User> getUsers();

    User getOrCreate(String userAuthId);

    User getUserById(String userId) throws UserNotFoundException;

    User getUserByUserAuthId(String userAuthId) throws UserNotFoundException;

    User updateUser(User user);
}
