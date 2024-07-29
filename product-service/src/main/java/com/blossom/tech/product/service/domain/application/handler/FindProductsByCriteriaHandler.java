package com.blossom.tech.product.service.domain.application.handler;

import com.blossom.tech.domain.mediator.RequestHandler;
import com.blossom.tech.domain.valueobject.Money;
import com.blossom.tech.product.service.domain.application.dto.query.FindProductsByCriteria;
import com.blossom.tech.product.service.domain.application.dto.response.ProductsResponse;
import com.blossom.tech.product.service.domain.application.mapper.ProductDomainMapper;
import com.blossom.tech.product.service.domain.core.cosntant.ProductDomainConstant;
import com.blossom.tech.product.service.domain.core.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class FindProductsByCriteriaHandler implements RequestHandler<FindProductsByCriteria, List<ProductsResponse>> {

    private final ProductRepository productRepository;
    private final ProductDomainMapper productDomainMapper;

    public FindProductsByCriteriaHandler(ProductRepository productRepository, ProductDomainMapper productDomainMapper) {
        this.productRepository = productRepository;
        this.productDomainMapper = productDomainMapper;
    }

    @Override
    public List<ProductsResponse> handle(FindProductsByCriteria request) {
        List<ProductsResponse> products = productRepository.findAllByQuery(
                        request.getName(),
                        request.getCategoryId(),
                        new Money(request.getMinPrice()),
                        new Money(request.getMaxPrice())
                )
                .stream().map(
                        productDomainMapper::productToProductsResponse).collect(Collectors.toList()
                );
        log.info(ProductDomainConstant.PRODUCT_QUERIED_SUCCESSFULLY);
        return products;

    }
}
