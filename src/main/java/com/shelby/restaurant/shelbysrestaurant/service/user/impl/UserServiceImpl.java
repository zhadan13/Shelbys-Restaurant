package com.shelby.restaurant.shelbysrestaurant.service.user.impl;

import com.shelby.restaurant.shelbysrestaurant.controller.user.resource.UserCreateRequest;
import com.shelby.restaurant.shelbysrestaurant.controller.user.resource.UserUpdateRequest;
import com.shelby.restaurant.shelbysrestaurant.exception.UserAlreadyExistsException;
import com.shelby.restaurant.shelbysrestaurant.exception.UserNotFoundException;
import com.shelby.restaurant.shelbysrestaurant.exception.ValidationException;
import com.shelby.restaurant.shelbysrestaurant.mapper.user.UserMapper;
import com.shelby.restaurant.shelbysrestaurant.model.user.User;
import com.shelby.restaurant.shelbysrestaurant.repository.user.UserRepository;
import com.shelby.restaurant.shelbysrestaurant.service.user.UserService;
import com.shelby.restaurant.shelbysrestaurant.service.validation.EmailValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EmailValidator emailValidator;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User createUser(UserCreateRequest createRequest) {
        log.info("Creating new user");
        User user = User.builder().enabled(false).locked(false).build();
        userMapper.mapUserCreateRequestToUser(createRequest, user);
        Optional<User> optionalCurrentUser = userRepository
                .findUserByEmailOrPhoneNumber(user.getEmail(), user.getPhoneNumber());
        if (optionalCurrentUser.isPresent()) {
            log.error("Can't create new user. User with requested email or phone already exists");
            throw new UserAlreadyExistsException("User with requested email or phone already exists!");
        }
        final String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    @Override
    public void updateUser(String userId, UserUpdateRequest userUpdateRequest) {
        log.info("Updating user with id {}", userId);
        userRepository.findById(userId)
                .ifPresentOrElse(user -> {
                    userMapper.mapUserUpdateRequestToUser(userUpdateRequest, user);
                    userRepository.save(user);
                }, () -> {
                    log.error("User with id {} not found", userId);
                    throw new UserNotFoundException("User with id " + userId + " not found!");
                });
    }

    @Override
    public User getUserById(String userId) {
        log.info("Retrieving user by id {}", userId);
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id " + userId + " not found!"));
    }

    @Override
    public User getUserByEmail(String email) {
        log.info("Retrieving user by email");
        if (!emailValidator.test(email)) {
            log.error("Passed email not valid");
            throw new ValidationException("Passed email not valid!");
        }
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with passed email not found!"));
    }

    @Override
    public List<User> getAllUsers() {
        log.info("Retrieving all users");
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(String userId) {
        log.info("Deleting user by id {}", userId);
        userRepository.findById(userId)
                .ifPresentOrElse(userRepository::delete, () -> {
                    log.error("User with id " + userId + " not found");
                    throw new UserNotFoundException("User with id " + userId + " not found!");
                });
    }

    @Override
    public void enableUser(String email) {
        log.info("Enabling new user");
        if (!emailValidator.test(email)) {
            log.error("Passed email not valid");
            throw new ValidationException("Passed email not valid!");
        }
        userRepository.enableUser(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("Loading user by username");
        return getUserByEmail(email);
    }

    @Override
    public boolean comparePasswords(String password, String encryptedPassword) {
        return bCryptPasswordEncoder.matches(password, encryptedPassword);
    }
}
