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
     Boolean exists = false;
     User user = userRepo.findById(Long.parseLong(fav.getUserId())).orElseThrow(()->new AppException("User not found",HttpStatus.NOT_FOUND));

         Favorite favorite = Favorite.builder().imdbID(fav.getImdbID()).Year(fav.getYear()).Title(fav.getTitle())
                 .Type(EFilm.valueOf(fav.getType())).Poster(fav.getPoster()).isWatched(false)
                 .user(user).build();
         List<Favorite> userFavs = user.getFavorites();
         for(Favorite userFav : userFavs){
             if(userFav.getImdbID()== fav.getImdbID()){
                 exists = true;
             }
         }
         if(exists){
             throw new AppException("Already exists",HttpStatus.CONFLICT);
         }else{
         favRepo.save(favorite);
         }
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

    public void removeFavorite(String id) {
     favRepo.deleteById(Long.parseLong(id));
    }

    public void toggleIsWatched(String id, boolean isWatched) {
     Favorite fav = favRepo.findById(Long.parseLong(id)).orElseThrow(()->new AppException("ID Not found", HttpStatus.NOT_FOUND));
     fav.setIsWatched(isWatched);
        System.out.println(isWatched);
        System.out.println(fav.getIsWatched());
     favRepo.save(fav);
    }
}
