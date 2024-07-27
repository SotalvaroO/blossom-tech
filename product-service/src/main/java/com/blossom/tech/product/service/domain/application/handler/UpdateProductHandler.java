package com.blossom.tech.product.service.domain.application.handler;

import com.blossom.tech.domain.mediator.RequestHandler;
import com.blossom.tech.product.service.domain.application.dto.command.UpdateProduct;
import com.blossom.tech.product.service.domain.application.dto.response.ProductResponse;
import com.blossom.tech.product.service.domain.application.mapper.ProductDomainMapper;
import com.blossom.tech.product.service.domain.core.ProductDomainService;
import com.blossom.tech.product.service.domain.core.cosntant.ProductDomainConstant;
import com.blossom.tech.product.service.domain.core.entity.Product;
import com.blossom.tech.product.service.domain.core.exception.ProductNotFoundException;
import com.blossom.tech.product.service.domain.core.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UpdateProductHandler implements RequestHandler<UpdateProduct, ProductResponse> {

    private final ProductRepository productRepository;
    private final ProductDomainService productDomainService;
    private final ProductDomainMapper productDomainMapper;

    public UpdateProductHandler(ProductRepository productRepository, ProductDomainService productDomainService, ProductDomainMapper productDomainMapper) {
        this.productRepository = productRepository;
        this.productDomainService = productDomainService;
        this.productDomainMapper = productDomainMapper;
    }

    @Override
    public ProductResponse handle(UpdateProduct request) {
        Product existingProduct = productRepository.findById(request.getId())
                .orElseThrow(() -> {
                    log.error(String.format(String.format(ProductDomainConstant.PRODUCT_NOT_FOUND, request.getId())));
                    return new ProductNotFoundException(String.format(ProductDomainConstant.PRODUCT_NOT_FOUND, request.getId()));
                });
        productDomainService.updateProduct(existingProduct, productDomainMapper.updateProductToProduct(request));
        Product updatedProduct = productRepository.save(existingProduct);
        log.info(String.format(ProductDomainConstant.PRODUCT_SUCCESSFULLY_UPDATED, updatedProduct.getId()));
        return productDomainMapper.productToProductResponse(updatedProduct);
    }
}
