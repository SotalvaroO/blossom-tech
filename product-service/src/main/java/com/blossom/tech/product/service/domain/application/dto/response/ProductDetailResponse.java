package com.blossom.tech.product.service.domain.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class ProductDetailResponse {
    private final String name;
    private final String description;
    private final Double price;
    private final List<CategoryDisplay> categories;
    private final Long stock;
}
