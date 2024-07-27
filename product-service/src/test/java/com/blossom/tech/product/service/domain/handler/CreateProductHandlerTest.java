package com.blossom.tech.product.service.domain.handler;

import com.blossom.tech.domain.mediator.Mediator;
import com.blossom.tech.product.service.ProductObjectFactory;
import com.blossom.tech.product.service.domain.ProductDomainTestConfiguration;
import com.blossom.tech.product.service.domain.application.dto.command.CreateProduct;
import com.blossom.tech.product.service.domain.application.dto.response.ProductResponse;
import com.blossom.tech.product.service.domain.application.mapper.ProductDomainMapper;
import com.blossom.tech.product.service.domain.core.cosntant.ProductDomainConstant;
import com.blossom.tech.product.service.domain.core.entity.Product;
import com.blossom.tech.product.service.domain.core.exception.ProductDomainException;
import com.blossom.tech.product.service.domain.core.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@SpringBootTest(classes = ProductDomainTestConfiguration.class)
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
        product.setId(null);
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
                () -> mediator.send(wrongPriceCreateProduct)
        );
        //Assert
        Assertions.assertEquals(ProductDomainConstant.PRICE_GREATER_THAN_ZERO, productDomainException.getMessage());
    }

    @Test
    void givenACreateProductCommandWithWrongId_whenAttemptToCreateProduct_thenShouldThrowProductDomainException() {
        //Arrange
        CreateProduct createProduct = ProductObjectFactory.createProduct;
        Product wrongIdProduct = ProductObjectFactory.wrongIdProduct;
        //Act
        when(productDomainMapper.createProductToProduct(createProduct)).thenReturn(wrongIdProduct);
        ProductDomainException productDomainException = Assertions.assertThrows(
                ProductDomainException.class,
                () -> mediator.send(createProduct)
        );
        //Assert
        Assertions.assertEquals(ProductDomainConstant.WRONG_STATE_TO_INITIALIZE, productDomainException.getMessage());
    }

    @Test
    void givenACreateProductCommandWithWrongStock_whenAttemptToCreateProduct_thenShouldThrowProductDomainException() {
        //Arrange
        CreateProduct wrongStockCreateProduct = ProductObjectFactory.createWrongStockProduct;
        Product wrongStockProduct = ProductObjectFactory.wrongStockProduct;
        //Act
        when(productDomainMapper.createProductToProduct(wrongStockCreateProduct)).thenReturn(wrongStockProduct);
        ProductDomainException productDomainException = Assertions.assertThrows(
                ProductDomainException.class,
                () -> mediator.send(wrongStockCreateProduct)
        );
        //Assert
        Assertions.assertEquals(ProductDomainConstant.STOCK_GREATER_THAN_EQUALS_ZERO, productDomainException.getMessage());
    }

}
