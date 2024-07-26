package com.blossom.tech.product.service.domain.core.repository;

import com.blossom.tech.domain.valueobject.Money;
import com.blossom.tech.product.service.domain.core.entity.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {
    Product save(Product product);

    Optional<Product> findById(UUID id);

    List<Product> findAllByQuery(
            String name,
            UUID categoryId,
            Money initialPriceRange,
            Money finalPriceRange
    );

    Optional<Product> update(UUID id, Product product);

    Optional<Product> deleteById(UUID id);
}
