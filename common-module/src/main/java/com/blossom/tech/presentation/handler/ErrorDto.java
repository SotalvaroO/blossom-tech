package com.blossom.tech.presentation.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class ErrorDto {
    private final String code;
    private final String message;

}
