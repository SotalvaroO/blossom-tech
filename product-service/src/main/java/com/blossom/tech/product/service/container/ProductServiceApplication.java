package com.blossom.tech.product.service.container;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"com.blossom.tech.product.service.infrastructure"})
@EntityScan(basePackages = {"com.blossom.tech.product.service.infrastructure"})
@SpringBootApplication(scanBasePackages = "com.blossom.tech")
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class,args);
    }

}
