package com.blossom.tech.product.service.domain.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Getter
public class ProductResponse {
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final Long stock;
}
