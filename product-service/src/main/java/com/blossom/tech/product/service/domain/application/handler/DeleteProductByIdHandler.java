package com.blossom.tech.product.service.domain.application.handler;

import com.blossom.tech.domain.mediator.RequestHandler;
import com.blossom.tech.product.service.domain.application.dto.command.DeleteProductById;
import com.blossom.tech.product.service.domain.application.dto.response.ProductResponse;
import com.blossom.tech.product.service.domain.application.mapper.ProductDomainMapper;
import com.blossom.tech.product.service.domain.core.entity.Product;
import com.blossom.tech.product.service.domain.core.exception.ProductNotFoundException;
import com.blossom.tech.product.service.domain.core.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeleteProductByIdHandler implements RequestHandler<DeleteProductById, ProductResponse> {

    private final ProductRepository productRepository;
    private final ProductDomainMapper productDomainMapper;

    public DeleteProductByIdHandler(ProductRepository productRepository, ProductDomainMapper productDomainMapper) {
        this.productRepository = productRepository;
        this.productDomainMapper = productDomainMapper;
    }

    @Override
    public ProductResponse handle(DeleteProductById request) {
        Product deletedProduct = productRepository.deleteById(request.getId())
                .orElseThrow(() -> {
                    log.error(String.format(String.format("Product with id: %s was not found", request.getId())));
                    return new ProductNotFoundException(String.format("Product with id: %s was not found", request.getId()));
                });
        log.info(String.format("Product with id: %s was successfully updated", deletedProduct.getId()));
        return productDomainMapper.productToProductResponse(deletedProduct);
    }
}
