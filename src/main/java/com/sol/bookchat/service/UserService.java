package com.sol.bookchat.service;

import com.sol.bookchat.dto.UserResponse;
import com.sol.bookchat.model.User;
import com.sol.bookchat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse getUserByEmail (String email){
       Optional<User> user = userRepository.findByEmail(email);
       if(user.isPresent()){
        return UserResponse.builder().firstName(user.get().getFirstName())
                .lastName(user.get().getLastName())
                .email(user.get().getEmail())
                .role(user.get().getRole()).build();
       }
       return null;
    }
}
