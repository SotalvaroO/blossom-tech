package com.blossom.tech.product.service.presentation.rest;

import com.blossom.tech.domain.mediator.Mediator;
import com.blossom.tech.product.service.domain.application.dto.command.CreateProduct;
import com.blossom.tech.product.service.domain.application.dto.response.ProductResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping(value = "/products")
public class ProductController {

    private final Mediator mediator;

    public ProductController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody CreateProduct createProduct) {
        log.info("Creating product");
        ProductResponse response = mediator.send(createProduct);
        log.info("Product was created");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
