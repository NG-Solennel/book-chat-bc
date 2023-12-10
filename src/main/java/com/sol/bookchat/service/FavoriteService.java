package com.sol.bookchat.service;

import com.sol.bookchat.config.UserAuthenticationProvider;
import com.sol.bookchat.dto.FavoriteDto;
import com.sol.bookchat.dto.UserDto;
import com.sol.bookchat.exceptions.AppException;
import com.sol.bookchat.model.EFilm;
import com.sol.bookchat.model.Favorite;
import com.sol.bookchat.model.User;
import com.sol.bookchat.repository.FavoriteRepository;
import com.sol.bookchat.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FavoriteService {
 private final FavoriteRepository favRepo;
 private final UserRepository userRepo;
 private final UserAuthenticationProvider userAuthenticationProvider;
 public void saveFavorite(FavoriteDto fav){
     Optional<User> user = userRepo.findById(fav.getUserId());
        if(!user.isPresent()){
            throw new AppException("User not found",HttpStatus.NOT_FOUND);
        }
         Favorite favorite = Favorite.builder().imdbID(fav.getImdbID()).Year(fav.getYear()).Title(fav.getTitle())
                 .Type(EFilm.valueOf(fav.getType())).Poster(fav.getPoster())
                 .user(user.get()).build();
         Boolean exists = user.get().getFavorites().contains(favorite);
         if(exists){
             throw new AppException("Already exists",HttpStatus.CONFLICT);
         }
         favRepo.save(favorite);
 }
 public List<Favorite> getFavorites(HttpServletRequest req){
     String authorizationHeader = req.getHeader("Authorization");
     if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
         String token = authorizationHeader.substring(7);
        UserDto userDto = userAuthenticationProvider.extractUser(token);
        Optional<User> user = userRepo.findById(userDto.getId());
        if(user.isPresent()){
           return user.get().getFavorites();
        }
     } else {
       throw new AppException("Unauthorized",HttpStatus.UNAUTHORIZED);
     }
     return List.of();
 }
}
