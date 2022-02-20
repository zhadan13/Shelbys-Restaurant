package com.shelby.restaurant.shelbysrestaurant.controller.authorization;

import com.shelby.restaurant.shelbysrestaurant.controller.user.resource.UserCreateRequest;
import com.shelby.restaurant.shelbysrestaurant.service.authorization.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody @Valid UserCreateRequest request) {
        return ResponseEntity.ok(registrationService.register(request));
    }

    @GetMapping("/confirm")
    public ResponseEntity<String> confirm(@RequestParam("token") String token) {
        return ResponseEntity.ok(registrationService.confirmToken(token));
    }
}
