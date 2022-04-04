package com.shelby.restaurant.shelbysrestaurant.controller.authorization;

import com.shelby.restaurant.shelbysrestaurant.controller.authorization.resource.LoginRequest;
import com.shelby.restaurant.shelbysrestaurant.security.Permissions;
import com.shelby.restaurant.shelbysrestaurant.service.authorization.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/login")
@PreAuthorize(Permissions.GLOBAL_SCOPE)
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<UserDetails> login(@RequestBody @Valid LoginRequest loginRequest) {
        return ResponseEntity.ok(loginService.login(loginRequest));
    }
}
