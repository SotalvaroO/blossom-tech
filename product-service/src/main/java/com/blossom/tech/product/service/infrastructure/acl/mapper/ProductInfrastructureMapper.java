package com.blossom.tech.product.service.infrastructure.acl.mapper;

import com.blossom.tech.product.service.domain.core.entity.Product;
import com.blossom.tech.product.service.infrastructure.entity.ProductEntity;

public interface ProductInfrastructureMapper {
    Product toDomainEntity(ProductEntity productEntity);

    ProductEntity toInfrastructureEntity(Product product);
}
