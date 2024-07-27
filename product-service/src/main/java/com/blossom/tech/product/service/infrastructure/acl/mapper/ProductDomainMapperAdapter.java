package com.blossom.tech.product.service.infrastructure.acl.mapper;

import com.blossom.tech.product.service.domain.application.dto.command.CreateProduct;
import com.blossom.tech.product.service.domain.application.dto.command.UpdateProduct;
import com.blossom.tech.product.service.domain.application.dto.response.ProductDetailResponse;
import com.blossom.tech.product.service.domain.application.dto.response.ProductResponse;
import com.blossom.tech.product.service.domain.application.mapper.ProductDomainMapper;
import com.blossom.tech.product.service.domain.core.entity.Product;

public class ProductDomainMapperAdapter implements ProductDomainMapper {
    @Override
    public Product createProductToProduct(CreateProduct createProduct) {
        return null;
    }

    @Override
    public ProductResponse productToProductResponse(Product product) {
        return null;
    }

    @Override
    public ProductDetailResponse productToProductDetailResponse(Product product) {
        return null;
    }

    @Override
    public Product updateProductToProduct(UpdateProduct updateProduct) {
        return null;
    }
}
