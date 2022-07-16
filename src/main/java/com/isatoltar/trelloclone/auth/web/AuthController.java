package com.isatoltar.trelloclone.auth.web;

import com.isatoltar.trelloclone.auth.business.UserService;
import com.isatoltar.trelloclone.auth.data.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class AuthController {

    final UserService userService;

    /**
     * R1: Registers a new User
     *
     * @param request   New user informations
     * @return          HTTP 201
     */
    @PostMapping
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        userService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}