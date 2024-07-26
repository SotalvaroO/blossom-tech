package com.blossom.tech.product.service.domain.application.dto.query;

import com.blossom.tech.domain.mediator.Request;
import com.blossom.tech.product.service.domain.application.dto.response.ProductDetailResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class FindProductById implements Request<ProductDetailResponse> {
    private final UUID id;
}
