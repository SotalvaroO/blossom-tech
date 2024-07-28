package com.blossom.tech.product.service.domain.handler;

import com.blossom.tech.domain.mediator.Mediator;
import com.blossom.tech.domain.valueobject.Money;
import com.blossom.tech.product.service.ProductObjectFactory;
import com.blossom.tech.product.service.domain.ProductDomainTestConfiguration;
import com.blossom.tech.product.service.domain.application.dto.query.FindProductsByCriteria;
import com.blossom.tech.product.service.domain.application.dto.response.ProductResponse;
import com.blossom.tech.product.service.domain.application.dto.response.ProductsResponse;
import com.blossom.tech.product.service.domain.application.mapper.ProductDomainMapper;
import com.blossom.tech.product.service.domain.core.entity.Product;
import com.blossom.tech.product.service.domain.core.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@SpringBootTest(classes = ProductDomainTestConfiguration.class)
public class FindProductsByCriteriaHandlerTest {
    @Autowired
    private Mediator mediator;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductDomainMapper productDomainMapper;

    @BeforeEach
    void setup() {

    }

    @Test
    void givenAFindProductsByCriteria_whenAttemptToFindAllByCriteria_thenShouldReturnListOfProductResponse() {
        FindProductsByCriteria findProductsByCriteria = ProductObjectFactory.findProductsByCriteria;
        List<Product> products = Collections.singletonList(ProductObjectFactory.product);
        List<ProductsResponse> expectedResponse = Collections.singletonList(ProductObjectFactory.pResponse);
        when(productRepository.findAllByQuery(
                findProductsByCriteria.getName(),
                findProductsByCriteria.getCategoryId(),
                new Money(findProductsByCriteria.getMinPrice()),
                new Money(findProductsByCriteria.getMaxPrice())
        )).thenReturn(products);
        when(productDomainMapper.productToProductsResponse(any(Product.class)))
                .thenAnswer(invocation -> {
                    Product product = invocation.getArgument(0);
                    return expectedResponse.get(0);
                });
        List<ProductsResponse> actualResponse = mediator.send(findProductsByCriteria);
        Assertions.assertEquals(expectedResponse, actualResponse);

    }
}
