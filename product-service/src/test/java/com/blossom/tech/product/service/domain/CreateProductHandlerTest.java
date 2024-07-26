package com.blossom.tech.product.service.domain;

import com.blossom.tech.domain.mediator.Mediator;
import com.blossom.tech.product.service.ProductTestConfiguration;
import com.blossom.tech.product.service.domain.application.mapper.ProductDomainMapper;
import com.blossom.tech.product.service.domain.core.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@SpringBootTest(classes = ProductTestConfiguration.class)
public class CreateProductHandlerTest {

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
    void givenACreateProductCommand_whenAttemptToCreateProduct_thenShouldReturnAProductResponse(){

    }

    @Test
    void givenACreateProductCommandWithWrongPrice_whenAttemptToCreateProduct_thenShouldThrowProductDomainException(){

    }

    @Test
    void givenACreateProductCommandWithWrongId_whenAttemptToCreateProduct_thenShouldThrowProductDomainException(){

    }

    @Test
    void givenACreateProductCommandWithWrongStock_whenAttemptToCreateProduct_thenShouldThrowProductDomainException(){

    }

}
