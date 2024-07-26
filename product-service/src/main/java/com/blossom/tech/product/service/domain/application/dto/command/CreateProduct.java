package com.blossom.tech.product.service.domain.application.dto.command;

import com.blossom.tech.domain.mediator.Request;
import com.blossom.tech.product.service.domain.application.dto.CategoryDisplay;
import com.blossom.tech.product.service.domain.application.dto.response.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
public class CreateProduct implements Request<ProductResponse> {
    private final String name;
    private final String description;
    private final Double price;
    private final List<CategoryDisplay> categories;
    private final Long stock;


}
