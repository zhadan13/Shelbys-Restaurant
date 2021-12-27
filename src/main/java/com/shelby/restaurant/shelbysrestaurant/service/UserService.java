package com.shelby.restaurant.shelbysrestaurant.service;

import com.shelby.restaurant.shelbysrestaurant.controller.resource.UserCreateRequest;
import com.shelby.restaurant.shelbysrestaurant.controller.resource.UserUpdateRequest;
import com.shelby.restaurant.shelbysrestaurant.model.user.User;

import java.util.List;

public interface UserService {

    User createUser(UserCreateRequest createRequest);

    void updateUser(Long userId, UserUpdateRequest updateRequest);

    User getUserById(Long userId);

    User getUserByEmail(String email);

    List<User> getAllUsers();

    void deleteUser(Long userId);

    void enableUser(String email);
}