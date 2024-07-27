package com.blossom.tech.product.service.domain.handler;

import com.blossom.tech.domain.mediator.Mediator;
import com.blossom.tech.product.service.ProductObjectFactory;
import com.blossom.tech.product.service.ProductTestConstant;
import com.blossom.tech.product.service.domain.ProductDomainTestConfiguration;
import com.blossom.tech.product.service.domain.application.dto.command.UpdateProduct;
import com.blossom.tech.product.service.domain.application.dto.response.ProductResponse;
import com.blossom.tech.product.service.domain.application.mapper.ProductDomainMapper;
import com.blossom.tech.product.service.domain.core.cosntant.ProductDomainConstant;
import com.blossom.tech.product.service.domain.core.entity.Product;
import com.blossom.tech.product.service.domain.core.exception.ProductDomainException;
import com.blossom.tech.product.service.domain.core.exception.ProductNotFoundException;
import com.blossom.tech.product.service.domain.core.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@SpringBootTest(classes = ProductDomainTestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UpdateProductHandlerTest {

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
    void givenAnUpdateProduct_whenAttemptToUpdateAProduct_ThenShouldReturnProductResponse() {
        UpdateProduct updateProduct = ProductObjectFactory.updateProduct;
        Product existingProduct = ProductObjectFactory.product;
        existingProduct.setId(ProductTestConstant.PRODUCT_ID);
        Product product = ProductObjectFactory.productToUpdate;
        ProductResponse expectedProductResponse = ProductObjectFactory.updatedProductResponse;
        when(productRepository.findById(updateProduct.getId())).thenReturn(Optional.of(existingProduct));
        when(productDomainMapper.updateProductToProduct(updateProduct)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(productDomainMapper.productToProductResponse(product)).thenReturn(expectedProductResponse);

        ProductResponse response = mediator.send(updateProduct);

        Assertions.assertEquals(expectedProductResponse, response);
    }

    @Test
    void givenAWrongUpdateProductId_whenAttemptToUpdateAProduct_thenShouldThrowProductNotFoundException(){
        UpdateProduct wrongUpdateProductId = ProductObjectFactory.wrongIdUpdateProduct;
        ProductNotFoundException notFoundException = Assertions.assertThrows(
                ProductNotFoundException.class,
                ()->mediator.send(wrongUpdateProductId)
        );
        Assertions.assertEquals(String.format(ProductDomainConstant.PRODUCT_NOT_FOUND, wrongUpdateProductId.getId()),notFoundException.getMessage());
    }

    @Test
    void givenAWrongUpdateProductPrice_whenAttemptToUpdateAProduct_theShouldThrowProductDomainException(){
        UpdateProduct wrongUpdateProductPrice = ProductObjectFactory.wrongPriceUpdateProduct;
        Product existingProduct = ProductObjectFactory.product;
        existingProduct.setId(ProductTestConstant.PRODUCT_ID);
        Product product = ProductObjectFactory.wrongPriceProductToUpdate;
        when(productRepository.findById(wrongUpdateProductPrice.getId())).thenReturn(Optional.of(existingProduct));
        when(productDomainMapper.updateProductToProduct(wrongUpdateProductPrice)).thenReturn(product);
        ProductDomainException domainException = Assertions.assertThrows(
                ProductDomainException.class,
                ()->mediator.send(wrongUpdateProductPrice)
        );
        Assertions.assertEquals(ProductDomainConstant.PRICE_GREATER_THAN_ZERO,domainException.getMessage());
    }
    @Test
    void givenAWrongUpdateProductStock_whenAttemptToUpdateAProduct_theShouldThrowProductDomainException(){
        UpdateProduct wrongUpdateProductStock = ProductObjectFactory.wrongStockUpdateProduct;
        Product existingProduct = ProductObjectFactory.product;
        existingProduct.setId(ProductTestConstant.PRODUCT_ID);
        Product product = ProductObjectFactory.wrongStockProductToUpdate;
        when(productRepository.findById(wrongUpdateProductStock.getId())).thenReturn(Optional.of(existingProduct));
        when(productDomainMapper.updateProductToProduct(wrongUpdateProductStock)).thenReturn(product);
        ProductDomainException domainException = Assertions.assertThrows(
                ProductDomainException.class,
                ()->mediator.send(wrongUpdateProductStock)
        );
        Assertions.assertEquals(ProductDomainConstant.STOCK_GREATER_THAN_EQUALS_ZERO,domainException.getMessage());
    }

}
