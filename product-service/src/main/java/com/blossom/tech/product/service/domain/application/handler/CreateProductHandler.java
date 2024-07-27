package com.blossom.tech.product.service.domain.application.handler;

import com.blossom.tech.domain.mediator.RequestHandler;
import com.blossom.tech.product.service.domain.application.dto.command.CreateProduct;
import com.blossom.tech.product.service.domain.application.dto.response.ProductResponse;
import com.blossom.tech.product.service.domain.application.mapper.ProductDomainMapper;
import com.blossom.tech.product.service.domain.core.ProductDomainService;
import com.blossom.tech.product.service.domain.core.cosntant.ProductDomainConstant;
import com.blossom.tech.product.service.domain.core.entity.Product;
import com.blossom.tech.product.service.domain.core.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateProductHandler implements RequestHandler<CreateProduct, ProductResponse> {

    private final ProductRepository productRepository;
    private final ProductDomainService productDomainService;
    private final ProductDomainMapper productDomainMapper;

    public CreateProductHandler(ProductRepository productRepository, ProductDomainService productDomainService, ProductDomainMapper productDomainMapper) {
        this.productRepository = productRepository;
        this.productDomainService = productDomainService;
        this.productDomainMapper = productDomainMapper;
    }

    @Override
    public ProductResponse handle(CreateProduct request) {
        Product product = productDomainMapper.createProductToProduct(request);
        productDomainService.createProduct(product);
        Product createdProduct = productRepository.save(product);
        log.info(String.format(ProductDomainConstant.PRODUCT_CREATED_SUCCESSFULLY, createdProduct));
        return productDomainMapper.productToProductResponse(createdProduct);
    }
}
