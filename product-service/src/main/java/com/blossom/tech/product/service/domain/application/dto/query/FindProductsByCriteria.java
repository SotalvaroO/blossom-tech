package com.blossom.tech.product.service.domain.application.dto.query;

import com.blossom.tech.domain.mediator.Request;
import com.blossom.tech.product.service.domain.application.dto.response.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class FindProductsByCriteria implements Request<List<ProductResponse>> {
    private String name;
    private UUID categoryId;
    private Double minPrice;
    private Double maxPrice;

}
