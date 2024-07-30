package com.blossom.tech.order.service.domain.application.dto.command;

import com.blossom.tech.domain.mediator.Request;
import com.blossom.tech.order.service.domain.application.dto.ProductItem;
import com.blossom.tech.order.service.domain.application.dto.response.CreateOrderResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Builder
public class CreateOrder implements Request<CreateOrderResponse> {
    private final UUID userId;
    private final BigDecimal price;
    private final List<ProductItem> items;
}
