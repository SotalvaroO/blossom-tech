package com.blossom.tech.product.service.infrastructure.acl.mapper;

import com.blossom.tech.product.service.domain.core.entity.Category;
import com.blossom.tech.product.service.domain.core.entity.Product;
import com.blossom.tech.product.service.infrastructure.entity.CategoryEntity;
import com.blossom.tech.product.service.infrastructure.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductInfrastructureMapper {

    public ProductInfrastructureMapper INSTANCE = Mappers.getMapper(ProductInfrastructureMapper.class);

    @Mappings({
            @Mapping(source = "price", target = "price.amount"),
    })
    Product toDomainEntity(ProductEntity productEntity);

    @Mappings({
            @Mapping(source = "price.amount", target = "price"),
    })
    ProductEntity toInfrastructureEntity(Product product);

    /*    @Mappings({
                @Mapping(source = "id", target = "id"),
                @Mapping(source = "name", target = "name")
        })*/
    Category categoryEntityToCategory(CategoryEntity categoryEntity);
}
