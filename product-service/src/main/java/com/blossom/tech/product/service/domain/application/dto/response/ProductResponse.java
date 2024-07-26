package com.blossom.tech.product.service.domain.application.dto.response;

import com.blossom.tech.product.service.domain.application.dto.CategoryDisplay;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
public class ProductResponse {
    private final String name;
    private final String description;
    private final Double price;
    private final Long stock;
}
