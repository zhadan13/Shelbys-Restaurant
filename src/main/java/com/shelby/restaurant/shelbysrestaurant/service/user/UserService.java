package com.shelby.restaurant.shelbysrestaurant.service.user;

import com.shelby.restaurant.shelbysrestaurant.controller.user.resource.UserCreateRequest;
import com.shelby.restaurant.shelbysrestaurant.controller.user.resource.UserUpdateRequest;
import com.shelby.restaurant.shelbysrestaurant.model.user.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User createUser(UserCreateRequest createRequest);

    void updateUser(String userId, UserUpdateRequest updateRequest);

    User getUserById(String userId);

    User getUserByEmail(String email);

    List<User> getAllUsers();

    void deleteUser(String userId);

    void enableUser(String email);

    boolean comparePasswords(String password, String encryptedPassword);
}
