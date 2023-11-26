package com.sol.bookchat.controller;

import com.sol.bookchat.dto.AuthRequest;
import com.sol.bookchat.dto.AuthResponse;
import com.sol.bookchat.dto.RegisterRequest;
import com.sol.bookchat.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        AuthResponse res = service.register(request);
        if(res.getToken() == "409"){
            return ResponseEntity.status(409).build();
        }
        return ResponseEntity.ok().body(res) ;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request){
        return ResponseEntity.ok(service.login(request));
    }
}
