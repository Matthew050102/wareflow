package com.matthew050102.wareflow.auth;

import com.matthew050102.wareflow.user.User;
import com.matthew050102.wareflow.user.UserRepository;
import com.matthew050102.wareflow.auth.dto.AuthResponse;
import com.matthew050102.wareflow.auth.dto.LoginRequest;
import com.matthew050102.wareflow.auth.dto.RegisterRequest;
import com.matthew050102.wareflow.security.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new IllegalArgumentException("Username already exists!");
        }
        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("User with this email already exists!");
        }

        User user = new User(
                request.firstName(),
                request.lastName(),
                request.username(),
                request.email(),
                passwordEncoder.encode(request.password())
        );

        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);
        return new AuthResponse(jwtToken);
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new IllegalArgumentException("Invalid data!"));

        String jwtToken = jwtService.generateToken(user);
        return new AuthResponse(jwtToken);
    }

}
