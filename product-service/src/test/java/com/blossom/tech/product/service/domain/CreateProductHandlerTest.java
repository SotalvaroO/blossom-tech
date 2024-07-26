package com.blossom.tech.product.service.domain;

import com.blossom.tech.domain.mediator.Mediator;
import com.blossom.tech.product.service.ProductObjectFactory;
import com.blossom.tech.product.service.ProductTestConfiguration;
import com.blossom.tech.product.service.domain.application.dto.command.CreateProduct;
import com.blossom.tech.product.service.domain.application.dto.response.ProductResponse;
import com.blossom.tech.product.service.domain.application.mapper.ProductDomainMapper;
import com.blossom.tech.product.service.domain.core.entity.Product;
import com.blossom.tech.product.service.domain.core.exception.ProductDomainException;
import com.blossom.tech.product.service.domain.core.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;

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
    void givenACreateProductCommand_whenAttemptToCreateProduct_thenShouldReturnAProductResponse() {
        //Arrange
        CreateProduct createProduct = ProductObjectFactory.createProduct;
        Product product = ProductObjectFactory.product;
        ProductResponse productResponse = ProductObjectFactory.response;
        when(productDomainMapper.createProductToProduct(createProduct)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(productDomainMapper.productToProductResponse(product)).thenReturn(productResponse);
        //Act
        ProductResponse response = mediator.send(createProduct);
        //Assert
        Assertions.assertEquals(productResponse, response);
    }

    @Test
    void givenACreateProductCommandWithWrongPrice_whenAttemptToCreateProduct_thenShouldThrowProductDomainException() {
        //Arrange
        CreateProduct wrongPriceCreateProduct = ProductObjectFactory.createWrongPriceProduct;
        Product wrongPriceProduct = ProductObjectFactory.wrongPriceProduct;
        //Act
        when(productDomainMapper.createProductToProduct(wrongPriceCreateProduct)).thenReturn(wrongPriceProduct);
        ProductDomainException productDomainException = Assertions.assertThrows(
                ProductDomainException.class,
                ()-> mediator.send(wrongPriceCreateProduct)
        );
        //Assert
        Assertions.assertEquals("The price must be greater than zero!",productDomainException.getMessage());
    }

    @Test
    void givenACreateProductCommandWithWrongId_whenAttemptToCreateProduct_thenShouldThrowProductDomainException() {

    }

    @Test
    void givenACreateProductCommandWithWrongStock_whenAttemptToCreateProduct_thenShouldThrowProductDomainException() {

    }

}
