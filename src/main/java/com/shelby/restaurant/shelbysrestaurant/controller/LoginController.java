package com.shelby.restaurant.shelbysrestaurant.controller;

import com.shelby.restaurant.shelbysrestaurant.controller.resource.LoginRequest;
import com.shelby.restaurant.shelbysrestaurant.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<UserDetails> login(@RequestBody @Valid LoginRequest request, HttpServletRequest servletRequest) {
        UserDetails user = loginService.login(request);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        authenticationToken.setDetails(new WebAuthenticationDetails(servletRequest));

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        HttpSession session = servletRequest.getSession(true);
        // HttpSession session = request.getSessionId(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

        log.debug("Logging in with [{}]", authentication.getPrincipal());

        return ResponseEntity.ok(user);
    }
}
