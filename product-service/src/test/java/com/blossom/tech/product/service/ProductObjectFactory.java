package com.blossom.tech.product.service;

import com.blossom.tech.domain.valueobject.Money;
import com.blossom.tech.product.service.domain.application.dto.command.CreateProduct;
import com.blossom.tech.product.service.domain.application.dto.command.DeleteProductById;
import com.blossom.tech.product.service.domain.application.dto.command.UpdateProduct;
import com.blossom.tech.product.service.domain.application.dto.query.FindProductById;
import com.blossom.tech.product.service.domain.application.dto.query.FindProductsByCriteria;
import com.blossom.tech.product.service.domain.application.dto.response.CategoryDisplay;
import com.blossom.tech.product.service.domain.application.dto.response.ProductDetailResponse;
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

    public static Product wrongStockProduct = Product.builder()
            .name(ProductTestConstant.PRODUCT_NAME)
            .description(ProductTestConstant.PRODUCT_DESCRIPTION)
            .price(new Money(BigDecimal.valueOf(ProductTestConstant.PRICE)))
            .categories(List.of(firstCategory, secondCategory))
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

    public static DeleteProductById deleteProductById = DeleteProductById.builder()
            .id(ProductTestConstant.PRODUCT_ID)
            .build();

    public static DeleteProductById wrongDeleteProductById = DeleteProductById.builder()
            .id(ProductTestConstant.WRONG_PRODUCT_ID)
            .build();

    public static UpdateProduct updateProduct = UpdateProduct.builder()
            .id(ProductTestConstant.PRODUCT_ID)
            .name(ProductTestConstant.UPDATED_PRODUCT_NAME)
            .description(ProductTestConstant.UPDATED_PRODUCT_DESCRIPTION)
            .price(ProductTestConstant.UPDATED_PRICE)
            .stock(ProductTestConstant.UPDATED_STOCK)
            .categories(List.of(ProductTestConstant.FIRST_CATEGORY_ID))
            .build();

    public static Product productToUpdate = Product.builder()
            .id(ProductTestConstant.PRODUCT_ID)
            .name(ProductTestConstant.UPDATED_PRODUCT_NAME)
            .description(ProductTestConstant.UPDATED_PRODUCT_DESCRIPTION)
            .price(new Money(BigDecimal.valueOf(ProductTestConstant.UPDATED_PRICE)))
            .categories(List.of(new Category(ProductTestConstant.FIRST_CATEGORY_ID, null)))
            .stock(ProductTestConstant.UPDATED_STOCK)
            .build();

    public static ProductResponse updatedProductResponse = ProductResponse.builder()
            .name(ProductTestConstant.UPDATED_PRODUCT_NAME)
            .description(ProductTestConstant.UPDATED_PRODUCT_DESCRIPTION)
            .price(ProductTestConstant.UPDATED_PRICE)
            .stock(ProductTestConstant.UPDATED_STOCK)
            .build();

    public static UpdateProduct wrongIdUpdateProduct = UpdateProduct.builder()
            .id(ProductTestConstant.WRONG_PRODUCT_ID)
            .name(ProductTestConstant.UPDATED_PRODUCT_NAME)
            .description(ProductTestConstant.UPDATED_PRODUCT_DESCRIPTION)
            .price(ProductTestConstant.UPDATED_PRICE)
            .stock(ProductTestConstant.UPDATED_STOCK)
            .categories(List.of(ProductTestConstant.FIRST_CATEGORY_ID))
            .build();

    public static UpdateProduct wrongPriceUpdateProduct = UpdateProduct.builder()
            .id(ProductTestConstant.PRODUCT_ID)
            .name(ProductTestConstant.UPDATED_PRODUCT_NAME)
            .description(ProductTestConstant.UPDATED_PRODUCT_DESCRIPTION)
            .price(ProductTestConstant.WRONG_PRICE)
            .stock(ProductTestConstant.UPDATED_STOCK)
            .categories(List.of(ProductTestConstant.FIRST_CATEGORY_ID))
            .build();

    public static Product wrongPriceProductToUpdate = Product.builder()
            .id(ProductTestConstant.PRODUCT_ID)
            .name(ProductTestConstant.UPDATED_PRODUCT_NAME)
            .description(ProductTestConstant.UPDATED_PRODUCT_DESCRIPTION)
            .price(new Money(BigDecimal.valueOf(ProductTestConstant.WRONG_PRICE)))
            .categories(List.of(new Category(ProductTestConstant.FIRST_CATEGORY_ID, null)))
            .stock(ProductTestConstant.UPDATED_STOCK)
            .build();

    public static UpdateProduct wrongStockUpdateProduct = UpdateProduct.builder()
            .id(ProductTestConstant.PRODUCT_ID)
            .name(ProductTestConstant.UPDATED_PRODUCT_NAME)
            .description(ProductTestConstant.UPDATED_PRODUCT_DESCRIPTION)
            .price(ProductTestConstant.PRICE)
            .stock(ProductTestConstant.WRONG_STOCK)
            .categories(List.of(ProductTestConstant.FIRST_CATEGORY_ID))
            .build();

    public static Product wrongStockProductToUpdate = Product.builder()
            .id(ProductTestConstant.PRODUCT_ID)
            .name(ProductTestConstant.UPDATED_PRODUCT_NAME)
            .description(ProductTestConstant.UPDATED_PRODUCT_DESCRIPTION)
            .price(new Money(BigDecimal.valueOf(ProductTestConstant.PRICE)))
            .categories(List.of(new Category(ProductTestConstant.FIRST_CATEGORY_ID, null)))
            .stock(ProductTestConstant.WRONG_STOCK)
            .build();

    public static FindProductById findProductById = new FindProductById(ProductTestConstant.PRODUCT_ID);
    public static FindProductById wrongIdFindProductById = new FindProductById(ProductTestConstant.WRONG_PRODUCT_ID);

    public static ProductDetailResponse productDetailResponse = ProductDetailResponse.builder()
            .name(ProductTestConstant.PRODUCT_NAME)
            .description(ProductTestConstant.PRODUCT_DESCRIPTION)
            .price(ProductTestConstant.PRICE)
            .stock(ProductTestConstant.STOCK)
            .categories(List.of(
                    new CategoryDisplay(firstCategory.getId(), firstCategory.getName()),
                    new CategoryDisplay(secondCategory.getId(), secondCategory.getName())
            ))
            .build();

    public static FindProductsByCriteria findProductsByCriteria = FindProductsByCriteria.builder()
            .name(ProductTestConstant.PRODUCT_NAME)
            .categoryId(ProductTestConstant.FIRST_CATEGORY_ID)
            .minPrice(1.0)
            .maxPrice(40000.0)
            .build();
}
