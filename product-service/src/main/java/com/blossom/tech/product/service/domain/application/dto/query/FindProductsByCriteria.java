package com.blossom.tech.product.service.domain.application.dto.query;

import com.blossom.tech.domain.mediator.Request;
import com.blossom.tech.product.service.domain.application.dto.response.ProductResponse;
import com.blossom.tech.product.service.domain.application.dto.response.ProductsResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class FindProductsByCriteria implements Request<List<ProductsResponse>> {
    private String name;
    private UUID categoryId;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;

}
