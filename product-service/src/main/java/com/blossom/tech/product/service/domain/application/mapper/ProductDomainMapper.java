package com.blossom.tech.product.service.domain.application.mapper;

import com.blossom.tech.product.service.domain.application.dto.command.CreateProduct;
import com.blossom.tech.product.service.domain.application.dto.command.UpdateProduct;
import com.blossom.tech.product.service.domain.application.dto.response.ProductDetailResponse;
import com.blossom.tech.product.service.domain.application.dto.response.ProductResponse;
import com.blossom.tech.product.service.domain.core.entity.Product;

public interface ProductDomainMapper {
    Product createProductToProduct(CreateProduct createProduct);

    ProductResponse productToProductResponse(Product product);

    ProductDetailResponse productToProductDetailResponse(Product product);

    Product updateProductToProduct(UpdateProduct updateProduct);
}
