package com.sol.bookchat.service;

import com.sol.bookchat.dto.AuthRequest;
import com.sol.bookchat.dto.AuthResponse;
import com.sol.bookchat.dto.RegisterRequest;
import com.sol.bookchat.dto.UserResponse;
import com.sol.bookchat.model.Role;
import com.sol.bookchat.model.User;
import com.sol.bookchat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepo;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .role(Role.USER)
                .password(passwordEncoder.encode(request.getPassword()))
                .build();


        UserResponse exists = userService.getUserByEmail(request.getEmail());
        if(exists != null){
            return AuthResponse.builder().token("409").build();
        }
        userRepo.save(user);

        String jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder().token(jwtToken).build();
    }
    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                      request.getEmail(),
                      request.getPassword()
              ));
        User user = userRepo.findByEmail(request.getEmail()).orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder().token(jwtToken).build();
    }
}
