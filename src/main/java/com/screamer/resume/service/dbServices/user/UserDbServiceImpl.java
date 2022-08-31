package com.screamer.resume.service.dbServices.user;

import com.screamer.resume.entity.User;
import com.screamer.resume.exceptions.user.UserNotFoundException;
import com.screamer.resume.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDbServiceImpl implements UserDbService {

    private final UserRepository userRepository;

    public UserDbServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getOrCreate(String userAuthId) {
        try {
            return getUserByUserAuthId(userAuthId);
        } catch (UserNotFoundException e) {
            User createdUser = new User(userAuthId);
            return userRepository.save(createdUser);
        }
    }

    @Override
    public User getUserById(String userId) throws UserNotFoundException {
        Optional<User> foundedUser = userRepository.findById(userId);
        if (!foundedUser.isPresent()) {
            throw new UserNotFoundException(userId);
        }

        return foundedUser.get();
    }

    @Override
    public User getUserByUserAuthId(String userAuthId) throws UserNotFoundException {
        Optional<User> foundedUser = userRepository.findByUserAuthId(userAuthId);
        if (!foundedUser.isPresent()) {
            throw new UserNotFoundException(userAuthId);
        }

        return foundedUser.get();
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

}
