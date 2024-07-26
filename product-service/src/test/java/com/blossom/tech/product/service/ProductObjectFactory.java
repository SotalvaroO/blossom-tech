package com.blossom.tech.product.service;

import com.blossom.tech.domain.valueobject.Money;
import com.blossom.tech.product.service.domain.application.dto.CategoryDisplay;
import com.blossom.tech.product.service.domain.application.dto.command.CreateProduct;
import com.blossom.tech.product.service.domain.application.dto.response.ProductResponse;
import com.blossom.tech.product.service.domain.core.entity.Category;
import com.blossom.tech.product.service.domain.core.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public class ProductObjectFactory {

    public static Category firstCategory = new Category(ProductTestConstant.FIRST_CATEGORY_ID, ProductTestConstant.FIRST_CATEGORY);
    public static Category secondCategory = new Category(ProductTestConstant.SECOND_CATEGORY_ID, ProductTestConstant.SECOND_CATEGORY);

    public static CreateProduct createProduct = CreateProduct.builder()
            .name(ProductTestConstant.PRODUCT_NAME)
            .description(ProductTestConstant.PRODUCT_DESCRIPTION)
            .categories(List.of(
                    new CategoryDisplay(firstCategory.getId(), firstCategory.getName()),
                    new CategoryDisplay(secondCategory.getId(), secondCategory.getName())
            ))
            .price(ProductTestConstant.PRICE)
            .stock(ProductTestConstant.STOCK)
            .build();

    public static Product product = Product.builder()
            .name(ProductTestConstant.PRODUCT_NAME)
            .description(ProductTestConstant.PRODUCT_DESCRIPTION)
            .price(new Money(BigDecimal.valueOf(ProductTestConstant.PRICE)))
            .categories(List.of(firstCategory, secondCategory))
            .stock(ProductTestConstant.STOCK)
            .build();

    public static ProductResponse response = ProductResponse.builder()
            .name(ProductTestConstant.PRODUCT_NAME)
            .description(ProductTestConstant.PRODUCT_DESCRIPTION)
            .price(ProductTestConstant.PRICE)
            .stock(ProductTestConstant.STOCK)
            .build();

    public static CreateProduct createWrongPriceProduct = CreateProduct.builder()
            .name(ProductTestConstant.PRODUCT_NAME)
            .description(ProductTestConstant.PRODUCT_DESCRIPTION)
            .categories(List.of(
                    new CategoryDisplay(firstCategory.getId(), firstCategory.getName()),
                    new CategoryDisplay(secondCategory.getId(), secondCategory.getName())
            ))
            .price(ProductTestConstant.WRONG_PRICE)
            .stock(ProductTestConstant.STOCK)
            .build();

    public static Product wrongPriceProduct = Product.builder()
            .name(ProductTestConstant.PRODUCT_NAME)
            .description(ProductTestConstant.PRODUCT_DESCRIPTION)
            .price(new Money(BigDecimal.valueOf(ProductTestConstant.WRONG_PRICE)))
            .categories(List.of(firstCategory, secondCategory))
            .stock(ProductTestConstant.STOCK)
            .build();

    public static CreateProduct createWrongStockProduct = CreateProduct.builder()
            .name(ProductTestConstant.PRODUCT_NAME)
            .description(ProductTestConstant.PRODUCT_DESCRIPTION)
            .categories(List.of(
                    new CategoryDisplay(firstCategory.getId(), firstCategory.getName()),
                    new CategoryDisplay(secondCategory.getId(), secondCategory.getName())
            ))
            .price(ProductTestConstant.PRICE)
            .stock(ProductTestConstant.WRONG_STOCK)
            .build();

    public static Product wrongIdProduct = Product.builder()
            .id(ProductTestConstant.WRONG_PRODUCT_ID)
            .name(ProductTestConstant.PRODUCT_NAME)
            .description(ProductTestConstant.PRODUCT_DESCRIPTION)
            .price(new Money(BigDecimal.valueOf(ProductTestConstant.PRICE)))
            .categories(List.of(firstCategory, secondCategory))
            .stock(ProductTestConstant.STOCK)
            .build();


}
