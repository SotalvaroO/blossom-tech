package com.blossom.tech.product.service.infrastructure.datasource;

import com.blossom.tech.product.service.infrastructure.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductDatasource extends JpaRepository<ProductEntity, UUID> {

    @Query("SELECT p FROM ProductEntity p " +
            "LEFT JOIN p.categories c " +
            "WHERE (:name IS NULL OR p.name LIKE %:name%) " +
            "AND (:categoryId IS NULL OR c.id = :categoryId) " +
            "AND (:initialPriceRange IS NULL OR p.price >= :initialPriceRange) " +
            "AND (:finalPriceRange IS NULL OR p.price <= :finalPriceRange)")
    List<ProductEntity> findByCriteria(
            @Param("name") String name,
            @Param("categoryId") UUID categoryId,
            @Param("initialPriceRange") BigDecimal initialPriceRange,
            @Param("finalPriceRange") BigDecimal finalPriceRange
    );
}
