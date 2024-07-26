package com.blossom.tech.product.service;

import com.blossom.tech.domain.mediator.Mediator;
import com.blossom.tech.domain.mediator.MediatorImpl;
import com.blossom.tech.domain.mediator.RequestHandler;
import com.blossom.tech.product.service.domain.application.dto.command.CreateProduct;
import com.blossom.tech.product.service.domain.application.handler.CreateProductHandler;
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
public class ProductTestConfiguration {

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
    public Mediator mediator() {
        return new MediatorImpl(getHandlers());
    }

    private Map<Class<?>, RequestHandler<?, ?>> getHandlers() {
        HashMap<Class<?>, RequestHandler<?, ?>> handlers = new HashMap<>();
        handlers.put(CreateProduct.class, createProductHandler());
        return  handlers;
    }
}
