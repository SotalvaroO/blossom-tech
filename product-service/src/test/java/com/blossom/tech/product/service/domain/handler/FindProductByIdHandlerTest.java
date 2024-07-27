package com.blossom.tech.product.service.domain.handler;

import com.blossom.tech.domain.mediator.Mediator;
import com.blossom.tech.product.service.ProductObjectFactory;
import com.blossom.tech.product.service.domain.ProductDomainTestConfiguration;
import com.blossom.tech.product.service.domain.application.dto.query.FindProductById;
import com.blossom.tech.product.service.domain.application.dto.response.ProductDetailResponse;
import com.blossom.tech.product.service.domain.application.mapper.ProductDomainMapper;
import com.blossom.tech.product.service.domain.core.cosntant.ProductDomainConstant;
import com.blossom.tech.product.service.domain.core.entity.Product;
import com.blossom.tech.product.service.domain.core.exception.ProductNotFoundException;
import com.blossom.tech.product.service.domain.core.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@SpringBootTest(classes = ProductDomainTestConfiguration.class)
public class FindProductByIdHandlerTest {
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
    void givenAFindProductByIdQuery_whenAttemptToFindById_thenShouldReturnProductDetailResponse() {
        FindProductById findProductById = ProductObjectFactory.findProductById;
        Product product = ProductObjectFactory.product;
        ProductDetailResponse expectedResponse = ProductObjectFactory.productDetailResponse;
        when(productRepository.findById(findProductById.getId())).thenReturn(Optional.of(product));
        when(productDomainMapper.productToProductDetailResponse(product)).thenReturn(expectedResponse);
        ProductDetailResponse response = mediator.send(findProductById);
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    void givenAWrongIdFindProductByIdQuery_whenAttemptToFindById_thenShouldThrowProductNotFoundException() {
        FindProductById wrongFindProductById = ProductObjectFactory.findProductById;
        ProductNotFoundException notFoundException = Assertions.assertThrows(
                ProductNotFoundException.class,
                () -> mediator.send(wrongFindProductById)
        );
        Assertions.assertEquals(String.format(ProductDomainConstant.PRODUCT_NOT_FOUND, wrongFindProductById.getId()), notFoundException.getMessage());
    }
}
