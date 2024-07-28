package com.blossom.tech.product.service.domain.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
public class ProductsResponse {
    private final UUID id;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final Long stock;
}
