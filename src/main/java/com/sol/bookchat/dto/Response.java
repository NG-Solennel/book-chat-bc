package com.sol.bookchat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response<T> {
    private int Status;
    private String message;
    private T data;
}
