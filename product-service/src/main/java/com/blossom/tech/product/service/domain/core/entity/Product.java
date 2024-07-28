package com.blossom.tech.product.service.domain.core.entity;

import com.blossom.tech.domain.entity.BaseEntity;
import com.blossom.tech.domain.valueobject.Money;
import com.blossom.tech.product.service.domain.core.cosntant.ProductDomainConstant;
import com.blossom.tech.product.service.domain.core.exception.ProductDomainException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Product extends BaseEntity<UUID> {
    private String name;
    private String description;
    private List<Category> categories;
    private Money price;
    private Long stock;

    private Product(Builder builder) {
        super.setId(builder.id);
        name = builder.name;
        description = builder.description;
        categories = builder.categories;
        price = builder.price;
        stock = builder.stock;
    }

    public static Builder builder() {
        return new Builder();
    }


    public void initializeProduct() {
        setId(UUID.randomUUID());
    }

    public void validateProductToUpdate() {
        validateExistingProduct();
        validatePrice();
        validateStock();
    }

    public void validateProduct() {
        validateInitialProduct();
        validatePrice();
        validateStock();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public Money getPrice() {
        return price;
    }

    public Long getStock() {
        return stock;
    }

    private void validatePrice() {
        if (!price.isGreaterThanZero())
            throw new ProductDomainException(ProductDomainConstant.PRICE_GREATER_THAN_ZERO);

    }

    private void validateStock() {
        if (stock < 0)
            throw new ProductDomainException(ProductDomainConstant.STOCK_GREATER_THAN_EQUALS_ZERO);

    }

    private boolean isIdNull() {
        return getId() == null;
    }

    private void validateInitialProduct() {
        if (!isIdNull())
            throw new ProductDomainException(ProductDomainConstant.WRONG_STATE_TO_INITIALIZE);
    }

    private void validateExistingProduct() {
        if (isIdNull())
            throw new ProductDomainException(ProductDomainConstant.ID_IS_NULL);

    }

    public void updateProduct(Product product) {
        setId(product.getId());
        name = Optional.ofNullable(product.name).orElse(name);
        description = Optional.ofNullable(product.description).orElse(description);
        categories = Optional.ofNullable(product.categories)
                .filter(List::isEmpty)
                .orElse(categories);
        price = Optional.ofNullable(product.price).orElse(price);
        stock = Optional.ofNullable(product.stock).orElse(stock);
    }

    public static final class Builder {
        private UUID id;
        private String name;
        private String description;
        private List<Category> categories;
        private Money price;
        private Long stock;

        private Builder() {
        }

        public Builder id(UUID val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder description(String val) {
            description = val;
            return this;
        }

        public Builder categories(List<Category> val) {
            categories = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder stock(Long val) {
            stock = val;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
