package com.isatoltar.trelloclone.auth.business;

import com.isatoltar.trelloclone.auth.data.RegisterRequest;
import com.isatoltar.trelloclone.auth.data.Role;
import com.isatoltar.trelloclone.auth.data.User;
import com.isatoltar.trelloclone.auth.data.UserRepository;
import com.isatoltar.trelloclone.shared.exception.ResourceAlreadyExistsException;
import com.isatoltar.trelloclone.shared.exception.ResourceNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class UserService {

    final UserRepository userRepository;
    final BCryptPasswordEncoder passwordEncoder;

    public void registerUser(RegisterRequest request) {

        String username = request.getUsername();
        User user = getUserByUsername(username);
        if (user != null)
            throw new ResourceAlreadyExistsException(
                    String.format("User with username = %s already exists", username)
            );

        user = new User();
        user.setName(request.getName());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Set<Role> roles = request.getRoles().stream().map(Role::new).collect(Collectors.toSet());
        user.setRoles(roles);

        userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException(String.format("User with username = %s does not exists", username))
                );
    }
}
