package com.blossom.tech.product.service.infrastructure;

import com.blossom.tech.product.service.domain.core.repository.ProductRepository;
import com.blossom.tech.product.service.infrastructure.datasource.ProductDatasource;
import com.blossom.tech.product.service.infrastructure.entity.CategoryEntity;
import com.blossom.tech.product.service.infrastructure.entity.ProductEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DataJpaTest
@ActiveProfiles("test")
public class ProductRepositoryAdapterTest {

    @Autowired
    private ProductDatasource productDatasource;
    @Autowired
    private ProductRepository productRepository;
    private ProductEntity productEntity;

    @BeforeEach
    void setup() {
        productEntity = productDatasource.save(
                ProductEntity.builder()
                        .id(UUID.randomUUID())
                        .name("Mac")
                        .description("Mac")
                        .price(BigDecimal.valueOf(3000))
                        .stock(3L)
                        .categories(
                                List.of(
                                        CategoryEntity.builder()
                                                .id(UUID.randomUUID())
                                                .name("Computer")
                                                .build()
                                )
                        )
                        .build()
        );
    }

    @Test
    void firstTest() {
        List<ProductEntity> entity = productDatasource.findByCriteria(
                "Mac",
                productEntity.getCategories().stream().findFirst().get().getId(),
                BigDecimal.valueOf(2000),
                BigDecimal.valueOf(2999)

        );
        System.out.println("A");
    }
}
