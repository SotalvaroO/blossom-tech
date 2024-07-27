package com.blossom.tech.product.service.domain.application.handler;

import com.blossom.tech.domain.mediator.RequestHandler;
import com.blossom.tech.domain.valueobject.Money;
import com.blossom.tech.product.service.domain.application.dto.query.FindProductsByCriteria;
import com.blossom.tech.product.service.domain.application.dto.response.ProductResponse;
import com.blossom.tech.product.service.domain.application.mapper.ProductDomainMapper;
import com.blossom.tech.product.service.domain.core.cosntant.ProductDomainConstant;
import com.blossom.tech.product.service.domain.core.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class FindProductsByCriteriaHandler implements RequestHandler<FindProductsByCriteria, List<ProductResponse>> {

    private final ProductRepository productRepository;
    private final ProductDomainMapper productDomainMapper;

    public FindProductsByCriteriaHandler(ProductRepository productRepository, ProductDomainMapper productDomainMapper) {
        this.productRepository = productRepository;
        this.productDomainMapper = productDomainMapper;
    }

    @Override
    public List<ProductResponse> handle(FindProductsByCriteria request) {
        List<ProductResponse> products = productRepository.findAllByQuery(
                        request.getName(),
                        request.getCategoryId(),
                        new Money(BigDecimal.valueOf(request.getMinPrice())),
                        new Money(BigDecimal.valueOf(request.getMaxPrice()))
                )
                .stream().map(
                        productDomainMapper::productToProductResponse).collect(Collectors.toList()
                );
        log.info(ProductDomainConstant.PRODUCT_QUERIED_SUCCESSFULLY);
        return products;

    }
}
