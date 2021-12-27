package com.shelby.restaurant.shelbysrestaurant.controller;

import com.shelby.restaurant.shelbysrestaurant.controller.resource.GetUserRequest;
import com.shelby.restaurant.shelbysrestaurant.controller.resource.UserCreateRequest;
import com.shelby.restaurant.shelbysrestaurant.controller.resource.UserUpdateRequest;
import com.shelby.restaurant.shelbysrestaurant.model.user.User;
import com.shelby.restaurant.shelbysrestaurant.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid UserCreateRequest createRequest) {
        User user = userService.createUser(createRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) {
        return ResponseEntity.ok(userService.getUserById(Long.valueOf(id)));
    }

    @PostMapping("/email")
    public ResponseEntity<User> getUserByEmail(@RequestBody @Valid GetUserRequest userRequest) {
        return ResponseEntity.ok(userService.getUserByEmail(userRequest.getEmail()));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody @Valid UserUpdateRequest updateRequest) {
        userService.updateUser(Long.valueOf(id), updateRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        userService.deleteUser(Long.valueOf(id));
        return ResponseEntity.ok().build();
    }
}
