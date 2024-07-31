package com.blossom.tech.product.service.domain.handler;

import com.blossom.tech.domain.mediator.Mediator;
import com.blossom.tech.product.service.ProductObjectFactory;
import com.blossom.tech.product.service.domain.ProductDomainTestConfiguration;
import com.blossom.tech.product.service.domain.application.dto.command.DeleteProductById;
import com.blossom.tech.product.service.domain.application.dto.response.ProductResponse;
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
public class DeleteProductByIdHandlerTest {

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
    void givenADeleteProductByIdCommand_whenAttemptDeleteProduct_thenShouldReturnProductResponse(){
        //Arrange
        DeleteProductById deleteProduct = ProductObjectFactory.deleteProductById;
        Product product = ProductObjectFactory.product;
        ProductResponse expectedProductResponse = ProductObjectFactory.response;
        when(productRepository.findById(deleteProduct.getId())).thenReturn(Optional.of(product));
        when(productRepository.deleteById(deleteProduct.getId())).thenReturn(Optional.of(product));
        when(productDomainMapper.productToProductResponse(product)).thenReturn(expectedProductResponse);
        //Act
        ProductResponse response = mediator.send(deleteProduct);
        //Assert
        Assertions.assertEquals(expectedProductResponse,response);
    }

    @Test
    void givenAWrongDeleteProductByIdCommand_whenAttemptDeleteProduct_thenShouldThrowProductNotFoundException(){
        //Arrange
        DeleteProductById deleteWrongIdProduct = ProductObjectFactory.wrongDeleteProductById;
        ProductNotFoundException notFoundException = Assertions.assertThrows(
                ProductNotFoundException.class,
                ()->mediator.send(deleteWrongIdProduct)
        );
        Assertions.assertEquals(String.format(ProductDomainConstant.PRODUCT_NOT_FOUND, deleteWrongIdProduct.getId()),notFoundException.getMessage());
    }

}
