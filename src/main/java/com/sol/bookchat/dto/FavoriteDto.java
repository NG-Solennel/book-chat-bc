package com.sol.bookchat.dto;

import com.sol.bookchat.model.EFilm;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavoriteDto {
    @NotEmpty
    private String imdbID;
    @NotEmpty
    private String title;
    @NotEmpty
    private String year;
    @NotEmpty
    private String type;
    @NotEmpty
    private String poster;
    @NotEmpty
    private String userId;
}
