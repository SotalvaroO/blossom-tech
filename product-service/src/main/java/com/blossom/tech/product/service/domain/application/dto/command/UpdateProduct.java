package com.blossom.tech.product.service.domain.application.dto.command;

import com.blossom.tech.domain.mediator.Request;
import com.blossom.tech.product.service.domain.application.dto.response.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
public class UpdateProduct implements Request<ProductResponse> {
    private UUID id;
    private final String name;
    private final String description;
    private final List<UUID> categories;
    private final BigDecimal price;
    private final Long stock;

    public void setId(UUID id) {
        this.id = id;
    }
}
