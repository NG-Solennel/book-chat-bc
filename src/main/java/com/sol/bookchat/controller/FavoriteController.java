package com.sol.bookchat.controller;

import com.sol.bookchat.dto.FavoriteDto;
import com.sol.bookchat.model.Favorite;
import com.sol.bookchat.service.FavoriteService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/favorite")
public class FavoriteController {
    private final FavoriteService favoriteService;

    @PostMapping
    public ResponseEntity saveFavorite(@RequestBody FavoriteDto favoriteDto) {
        favoriteService.saveFavorite(favoriteDto);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity<List<Favorite>> getFavorites(HttpServletRequest req){
        List<Favorite> favs = favoriteService.getFavorites(req);
                return ResponseEntity.status(200).body(favs);
    }
}
