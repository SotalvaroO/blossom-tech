package com.blossom.tech.product.service.domain.application.dto.command;

import com.blossom.tech.domain.mediator.Request;
import com.blossom.tech.product.service.domain.application.dto.response.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
public class UpdateProduct implements Request<ProductResponse> {
    private final UUID id;
    private final String name;
    private final String description;
    private final List<UUID> categories;
    private final Double price;
    private final Long stock;
}
