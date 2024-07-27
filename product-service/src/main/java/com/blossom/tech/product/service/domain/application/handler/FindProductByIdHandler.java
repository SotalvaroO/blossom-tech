package com.blossom.tech.product.service.domain.application.handler;

import com.blossom.tech.domain.mediator.RequestHandler;
import com.blossom.tech.product.service.domain.application.dto.query.FindProductById;
import com.blossom.tech.product.service.domain.application.dto.response.ProductDetailResponse;
import com.blossom.tech.product.service.domain.application.mapper.ProductDomainMapper;
import com.blossom.tech.product.service.domain.core.cosntant.ProductDomainConstant;
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

        return productRepository
                .findById(request.getId())
                .map(product -> {
                    log.info(String.format(ProductDomainConstant.PRODUCT_FOUND, product.getId()));
                    return productDomainMapper.productToProductDetailResponse(product);
                })
                .orElseThrow(() -> {
                            log.error(String.format(ProductDomainConstant.PRODUCT_NOT_FOUND, request.getId()));
                            return new ProductNotFoundException(
                                    String.format(ProductDomainConstant.PRODUCT_NOT_FOUND, request.getId())
                            );
                        }
                );
    }
}
