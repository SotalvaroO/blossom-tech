package com.blossom.tech;

import com.blossom.tech.domain.mediator.Mediator;
import com.blossom.tech.domain.mediator.MediatorImpl;
import com.blossom.tech.domain.mediator.RequestHandler;
import com.blossom.tech.order.service.domain.application.dto.command.CreateOrder;
import com.blossom.tech.order.service.domain.application.dto.query.FindHistoryByUserId;
import com.blossom.tech.order.service.domain.application.handler.CreateOrderHandler;
import com.blossom.tech.order.service.domain.application.handler.FindHistoryByUserIdHandler;
import com.blossom.tech.order.service.domain.application.mapper.OrderDomainMapper;
import com.blossom.tech.order.service.domain.core.repository.OrderRepository;
import com.blossom.tech.order.service.infrastructure.acl.adapter.OrderRepositoryAdapter;
import com.blossom.tech.order.service.infrastructure.acl.mapper.OrderDomainMapperAdapter;
import com.blossom.tech.order.service.infrastructure.acl.mapper.OrderInfrastructureMapper;
import com.blossom.tech.order.service.infrastructure.datasource.OrderDatasource;
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
import com.blossom.tech.product.service.infrastructure.acl.adapter.ProductRepositoryAdapter;
import com.blossom.tech.product.service.infrastructure.acl.mapper.ProductDomainMapperAdapter;
import com.blossom.tech.product.service.infrastructure.acl.mapper.ProductInfrastructureMapper;
import com.blossom.tech.product.service.infrastructure.datasource.ProductDatasource;
import com.blossom.tech.user.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class BeanConfiguration {

    @Autowired
    private ProductDatasource productDatasource;
    @Autowired
    private OrderDatasource orderDatasource;
    @Autowired
    private UserRepository userRepository;

    @Bean
    public ProductInfrastructureMapper productInfrastructureMapper() {
        return ProductInfrastructureMapper.INSTANCE;
    }

    @Bean
    public OrderInfrastructureMapper orderInfrastructureMapper() {
        return OrderInfrastructureMapper.INSTANCE;
    }

    @Bean
    public OrderRepository orderRepository() {
        return new OrderRepositoryAdapter(orderDatasource, orderInfrastructureMapper());
    }

    @Bean
    public FindHistoryByUserIdHandler findHistoryByUserIdHandler() {
        return new FindHistoryByUserIdHandler(orderRepository(), orderDomainMapper());
    }

    @Bean
    public OrderDomainMapper orderDomainMapper() {
        return new OrderDomainMapperAdapter();
    }

    @Bean
    public CreateOrderHandler createOrderHandler() {
        return new CreateOrderHandler(orderRepository(), orderDomainMapper(), productRepository());
    }

    @Bean
    public ProductRepository productRepository() {
        return new ProductRepositoryAdapter(productDatasource, productInfrastructureMapper());
    }

    @Bean
    public ProductDomainMapper productDomainMapper() {
        return new ProductDomainMapperAdapter();
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

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private Map<Class<?>, RequestHandler<?, ?>> getHandlers() {
        HashMap<Class<?>, RequestHandler<?, ?>> handlers = new HashMap<>();
        handlers.put(CreateProduct.class, createProductHandler());
        handlers.put(DeleteProductById.class, deleteProductByIdHandler());
        handlers.put(UpdateProduct.class, updateProductHandler());
        handlers.put(FindProductById.class, findProductByIdHandler());
        handlers.put(FindProductsByCriteria.class, findProductsByCriteriaHandler());
        handlers.put(CreateOrder.class, createOrderHandler());
        handlers.put(FindHistoryByUserId.class, findHistoryByUserIdHandler());
        return handlers;
    }
}
