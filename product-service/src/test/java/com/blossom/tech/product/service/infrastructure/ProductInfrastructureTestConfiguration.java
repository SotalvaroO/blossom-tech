package com.blossom.tech.product.service.infrastructure;

import com.blossom.tech.product.service.infrastructure.acl.adapter.ProductRepositoryAdapter;
import com.blossom.tech.product.service.infrastructure.acl.mapper.ProductInfrastructureMapper;
import com.blossom.tech.product.service.infrastructure.acl.mapper.ProductInfrastructureMapperAdapter;
import com.blossom.tech.product.service.infrastructure.datasource.ProductDatasource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"com.blossom.tech.product.service.infrastructure"})
@SpringBootApplication(scanBasePackages = "com.blossom.tech")
public class ProductInfrastructureTestConfiguration {

    @Autowired
    private ProductDatasource productDatasource;

    @Bean
    public ProductInfrastructureMapper productInfrastructureMapper(){
        return new ProductInfrastructureMapperAdapter();
    }
    @Bean
    public ProductRepositoryAdapter productRepositoryAdapter(){
        return new ProductRepositoryAdapter(productDatasource,productInfrastructureMapper());
    }

}
