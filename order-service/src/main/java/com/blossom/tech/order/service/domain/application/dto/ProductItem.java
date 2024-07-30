package com.blossom.tech.order.service.domain.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Builder
public class ProductItem {
    private final UUID productId;
    private final Integer quantity;
    private final BigDecimal price;
}
