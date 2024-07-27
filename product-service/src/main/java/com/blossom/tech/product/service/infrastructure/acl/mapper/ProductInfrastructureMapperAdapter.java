package com.blossom.tech.product.service.infrastructure.acl.mapper;

import com.blossom.tech.product.service.domain.core.entity.Product;
import com.blossom.tech.product.service.infrastructure.entity.ProductEntity;

public class ProductInfrastructureMapperAdapter implements ProductInfrastructureMapper{
    @Override
    public Product toDomainEntity(ProductEntity productEntity) {
        return null;
    }

    @Override
    public ProductEntity toInfrastructureEntity(Product product) {
        return null;
    }
}
