package com.blossom.tech.product.service.infrastructure.acl.adapter;

import com.blossom.tech.domain.valueobject.Money;
import com.blossom.tech.product.service.domain.core.entity.Product;
import com.blossom.tech.product.service.domain.core.repository.ProductRepository;
import com.blossom.tech.product.service.infrastructure.acl.mapper.ProductInfrastructureMapper;
import com.blossom.tech.product.service.infrastructure.datasource.ProductDatasource;
import com.blossom.tech.product.service.infrastructure.entity.CategoryEntity;
import com.blossom.tech.product.service.infrastructure.entity.ProductEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class ProductRepositoryAdapter implements ProductRepository {

    private final ProductDatasource productDatasource;
    private final ProductInfrastructureMapper productInfrastructureMapper;

    public ProductRepositoryAdapter(ProductDatasource productDatasource, ProductInfrastructureMapper productInfrastructureMapper) {
        this.productDatasource = productDatasource;
        this.productInfrastructureMapper = productInfrastructureMapper;
    }

    @Override
    public Product save(Product product) {
        return productInfrastructureMapper.toDomainEntity(
                productDatasource.save(
                        productInfrastructureMapper.toInfrastructureEntity(product)
                )
        );
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return productDatasource.findById(id)
                .map(productInfrastructureMapper::toDomainEntity);
    }

    @Override
    public List<Product> findAllByQuery(String name, UUID categoryId, Money initialPriceRange, Money finalPriceRange) {
        return productDatasource
                .findByCriteria(name, categoryId, initialPriceRange.getAmount(), finalPriceRange.getAmount())
                .stream()
                .map(productInfrastructureMapper::toDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Product> update(UUID id, Product product) {
        ProductEntity productEntity = productDatasource.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        productEntity.setId(product.getId());
        productEntity.setName(product.getName());
        productEntity.setDescription(product.getDescription());
        productEntity.setPrice(product.getPrice().getAmount());
        productEntity.setStock(product.getStock());
        productEntity.setCategories(
                product.getCategories()
                        .stream()
                        .map(category -> CategoryEntity.builder()
                                .id(category.getId())
                                .build())
                        .collect(Collectors.toList())
        );
        return Optional.of(productDatasource.save(productEntity))
                .map(productInfrastructureMapper::toDomainEntity);
    }

    @Override
    public Optional<Product> deleteById(UUID id) {
        Optional<ProductEntity> productEntity = productDatasource.findById(id);
        productEntity.ifPresent(productDatasource::delete);
        return productEntity.map(productInfrastructureMapper::toDomainEntity);
    }
}
