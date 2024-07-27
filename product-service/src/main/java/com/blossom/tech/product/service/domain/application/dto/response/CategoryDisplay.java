package com.blossom.tech.product.service.domain.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
public class CategoryDisplay {
    private final UUID id;
    private final String name;
}
