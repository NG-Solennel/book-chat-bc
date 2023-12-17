package com.sol.bookchat.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IsWatchedDto {
    @NotEmpty
    private String id;
    @NotEmpty
    private String isWatched;
}
