package com.springbites.service;

import com.springbites.dto.AuthRequest;
import com.springbites.dto.AuthResponse;
import com.springbites.entity.Role;
import com.springbites.entity.User;
import com.springbites.repository.UserRepository;
import com.springbites.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // ✅ Register
    public AuthResponse register(AuthRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // ✅ encoded
        user.setRole(Role.USER);

        userRepository.save(user);

        return new AuthResponse("User registered successfully", null);
    }

    private final JwtUtil jwtUtil;

    public AuthResponse login(AuthRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(
                user.getEmail(),
                user.getRole().name()
        );

        return new AuthResponse("Login successful", token);
    }
}