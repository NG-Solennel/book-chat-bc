package com.sol.bookchat.controller;

import com.sol.bookchat.dto.FavoriteDto;
import com.sol.bookchat.dto.IsWatchedDto;
import com.sol.bookchat.dto.Response;
import com.sol.bookchat.model.Favorite;
import com.sol.bookchat.service.FavoriteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/favorite")
public class FavoriteController {
    private final FavoriteService favoriteService;

    @PostMapping
    public ResponseEntity<Response<?>> saveFavorite(@RequestBody @Valid FavoriteDto favoriteDto) {
        favoriteService.saveFavorite(favoriteDto);
        return ResponseEntity.status(201).body(Response.builder().Status(201).message("Successfully created").build());
    }

    @GetMapping
    public ResponseEntity<List<Favorite>> getFavorites(HttpServletRequest req){
        List<Favorite> favs = favoriteService.getFavorites(req);
                return ResponseEntity.status(200).body(favs);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Response<?>> removeFavorite(@PathVariable("id") String id){
        favoriteService.removeFavorite(id);
        return ResponseEntity.status(204).body(Response.builder().Status(204).message("Deleted Successfully").build());
    }

    @PostMapping(value = "/isWatched")
    public ResponseEntity<Response<?>> toggleIsWatched(@RequestBody @Valid IsWatchedDto isWatchedDto){
        favoriteService.toggleIsWatched(isWatchedDto.getId(),Boolean.valueOf(isWatchedDto.getIsWatched()));
        return ResponseEntity.ok(Response.builder().message("Updated").Status(200).build());
    }
}
