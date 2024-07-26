package com.blossom.tech.product.service.domain.application.handler;

import com.blossom.tech.domain.mediator.RequestHandler;
import com.blossom.tech.product.service.domain.application.dto.query.FindProductById;
import com.blossom.tech.product.service.domain.application.dto.response.ProductDetailResponse;
import com.blossom.tech.product.service.domain.application.mapper.ProductDomainMapper;
import com.blossom.tech.product.service.domain.core.exception.ProductNotFoundException;
import com.blossom.tech.product.service.domain.core.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FindProductByIdHandler implements RequestHandler<FindProductById, ProductDetailResponse> {

    private final ProductRepository productRepository;
    private final ProductDomainMapper productDomainMapper;

    public FindProductByIdHandler(ProductRepository productRepository, ProductDomainMapper productDomainMapper) {
        this.productRepository = productRepository;
        this.productDomainMapper = productDomainMapper;
    }

    @Override
    public ProductDetailResponse handle(FindProductById request) {
        ProductDetailResponse productDetailResponse = productRepository
                .findById(request.getId())
                .map(product -> {
                    log.info(String.format("Product with id: %s was found", product.getId()));
                    return productDomainMapper.productToProductDetailResponse(product);
                })
                .orElseThrow(() -> {
                            log.error(String.format("Product with id: %s was found", request.getId()));
                            return new ProductNotFoundException(
                                    String.format("Product with id: %s was not found", request.getId())
                            );
                        }
                );

        return productDetailResponse;
    }
}
