package com.blossom.tech.product.service.domain;

import com.blossom.tech.domain.mediator.Mediator;
import com.blossom.tech.domain.mediator.MediatorImpl;
import com.blossom.tech.domain.mediator.RequestHandler;
import com.blossom.tech.product.service.domain.application.dto.command.CreateProduct;
import com.blossom.tech.product.service.domain.application.dto.command.DeleteProductById;
import com.blossom.tech.product.service.domain.application.dto.command.UpdateProduct;
import com.blossom.tech.product.service.domain.application.dto.query.FindProductById;
import com.blossom.tech.product.service.domain.application.dto.query.FindProductsByCriteria;
import com.blossom.tech.product.service.domain.application.handler.*;
import com.blossom.tech.product.service.domain.application.mapper.ProductDomainMapper;
import com.blossom.tech.product.service.domain.core.ProductDomainService;
import com.blossom.tech.product.service.domain.core.ProductDomainServiceImpl;
import com.blossom.tech.product.service.domain.core.repository.ProductRepository;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication(scanBasePackages = "com.blossom.tech")
public class ProductDomainTestConfiguration {

    @Bean
    public ProductRepository productRepository() {
        return Mockito.mock(ProductRepository.class);
    }

    @Bean
    public ProductDomainMapper productDomainMapper() {
        return Mockito.mock(ProductDomainMapper.class);
    }

    @Bean
    public ProductDomainService productDomainService() {
        return new ProductDomainServiceImpl();
    }

    @Bean
    public CreateProductHandler createProductHandler() {
        return new CreateProductHandler(productRepository(), productDomainService(), productDomainMapper());
    }

    @Bean
    public DeleteProductByIdHandler deleteProductByIdHandler() {
        return new DeleteProductByIdHandler(productRepository(), productDomainMapper());
    }

    @Bean
    public UpdateProductHandler updateProductHandler() {
        return new UpdateProductHandler(productRepository(), productDomainService(), productDomainMapper());
    }

    @Bean
    public Mediator mediator() {
        return new MediatorImpl(getHandlers());
    }

    @Bean
    public FindProductByIdHandler findProductByIdHandler() {
        return new FindProductByIdHandler(productRepository(), productDomainMapper());
    }

    @Bean
    public FindProductsByCriteriaHandler findProductsByCriteriaHandler() {
        return new FindProductsByCriteriaHandler(productRepository(), productDomainMapper());
    }

    private Map<Class<?>, RequestHandler<?, ?>> getHandlers() {
        HashMap<Class<?>, RequestHandler<?, ?>> handlers = new HashMap<>();
        handlers.put(CreateProduct.class, createProductHandler());
        handlers.put(DeleteProductById.class, deleteProductByIdHandler());
        handlers.put(UpdateProduct.class, updateProductHandler());
        handlers.put(FindProductById.class, findProductByIdHandler());
        handlers.put(FindProductsByCriteria.class, findProductsByCriteriaHandler());
        return handlers;
    }
}
