package com.blossom.tech.product.service.infrastructure.acl.mapper;

import com.blossom.tech.domain.valueobject.Money;
import com.blossom.tech.product.service.domain.application.dto.command.CreateProduct;
import com.blossom.tech.product.service.domain.application.dto.command.UpdateProduct;
import com.blossom.tech.product.service.domain.application.dto.response.CategoryDisplay;
import com.blossom.tech.product.service.domain.application.dto.response.ProductDetailResponse;
import com.blossom.tech.product.service.domain.application.dto.response.ProductResponse;
import com.blossom.tech.product.service.domain.application.dto.response.ProductsResponse;
import com.blossom.tech.product.service.domain.application.mapper.ProductDomainMapper;
import com.blossom.tech.product.service.domain.core.entity.Category;
import com.blossom.tech.product.service.domain.core.entity.Product;

public class ProductDomainMapperAdapter implements ProductDomainMapper {
    @Override
    public Product createProductToProduct(CreateProduct createProduct) {
        return Product.builder()
                .name(createProduct.getName())
                .description(createProduct.getDescription())
                .price(new Money(createProduct.getPrice()))
                .stock(createProduct.getStock())
                .categories(
                        createProduct.getCategories()
                                .stream()
                                .map(categoryDisplay ->
                                        new Category(categoryDisplay.getId(), categoryDisplay.getName()))
                                .toList()
                )
                .build();
    }

    @Override
    public ProductResponse productToProductResponse(Product product) {
        return ProductResponse.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice().getAmount())
                .stock(product.getStock())
                .build();
    }

    @Override
    public ProductDetailResponse productToProductDetailResponse(Product product) {
        return ProductDetailResponse.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice().getAmount())
                .stock(product.getStock())
                .categories(
                        product.getCategories()
                                .stream()
                                .map(category ->
                                        new CategoryDisplay(category.getId(), category.getName())).toList()
                )
                .build();
    }

    @Override
    public Product updateProductToProduct(UpdateProduct updateProduct) {
        return Product.builder()
                .id(updateProduct.getId())
                .name(updateProduct.getName())
                .description(updateProduct.getDescription())
                .price(new Money(updateProduct.getPrice()))
                .stock(updateProduct.getStock())
                .categories(
                        updateProduct.getCategories()
                                .stream()
                                .map(uuid -> new Category(uuid, null)).toList()
                )
                .build();
    }

    @Override
    public ProductsResponse productToProductsResponse(Product product) {
        return ProductsResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice().getAmount())
                .stock(product.getStock())
                .build();
    }
}
