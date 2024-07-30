package com.blossom.tech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"com.blossom.tech.product.service.infrastructure", "com.blossom.tech.user.service","com.blossom.tech.order.service"})
@EntityScan(basePackages = {"com.blossom.tech.product.service.infrastructure", "com.blossom.tech.user.service","com.blossom.tech.order.service"})
@SpringBootApplication(scanBasePackages = "com.blossom.tech")
public class BlossomTechApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlossomTechApplication.class, args);
    }
}