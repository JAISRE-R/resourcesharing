package com.example.resourcesharing.service;

import org.springframework.stereotype.Service;
import com.example.resourcesharing.repository.UserRepository;
import com.example.resourcesharing.model.User;
import com.example.resourcesharing.dto.LoginRequest;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return "Login successful. Welcome " + user.getName();
    }
}