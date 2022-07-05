package com.isatoltar.trelloclone.auth.web;

import com.isatoltar.trelloclone.auth.business.UserService;
import com.isatoltar.trelloclone.auth.data.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class AuthController {

    final UserService userService;

    @PostMapping
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {

        userService.registerUser(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
