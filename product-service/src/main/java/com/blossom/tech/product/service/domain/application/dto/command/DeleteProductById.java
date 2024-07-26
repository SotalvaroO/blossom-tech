package com.blossom.tech.product.service.domain.application.dto.command;

import com.blossom.tech.domain.mediator.Request;
import com.blossom.tech.product.service.domain.application.dto.response.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
public class DeleteProductById implements Request<ProductResponse> {
    private final UUID id;
}
