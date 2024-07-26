package com.blossom.tech.product.service.domain.core;

import com.blossom.tech.product.service.domain.core.entity.Product;

import java.util.UUID;

public interface ProductDomainService {
    void createProduct(Product product);
    void updateProduct(Product createdProduct, Product updatedProduct);
}
