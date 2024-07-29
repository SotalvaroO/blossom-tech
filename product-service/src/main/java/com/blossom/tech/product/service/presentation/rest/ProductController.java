package com.blossom.tech.product.service.presentation.rest;

import com.blossom.tech.domain.mediator.Mediator;
import com.blossom.tech.product.service.domain.application.dto.command.CreateProduct;
import com.blossom.tech.product.service.domain.application.dto.command.DeleteProductById;
import com.blossom.tech.product.service.domain.application.dto.command.UpdateProduct;
import com.blossom.tech.product.service.domain.application.dto.query.FindProductsByCriteria;
import com.blossom.tech.product.service.domain.application.dto.response.ProductResponse;
import com.blossom.tech.product.service.domain.application.dto.response.ProductsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/products")
public class ProductController {

    private final Mediator mediator;

    public ProductController(Mediator mediator) {
        this.mediator = mediator;
    }

    @GetMapping
    public ResponseEntity<List<ProductsResponse>> findProductByCriteria(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "categoryId", required = false) UUID categoryId,
            @RequestParam(name = "initialPriceRange", required = false) BigDecimal initialPriceRange,
            @RequestParam(name = "finalPriceRange", required = false) BigDecimal finalPriceRange
    ) {
        List<ProductsResponse> queriedProducts = mediator.send(
                FindProductsByCriteria.builder()
                        .name(name)
                        .categoryId(categoryId)
                        .minPrice(initialPriceRange)
                        .maxPrice(finalPriceRange)
                        .build()
        );
        log.info("Returning products by criteria");
        return ResponseEntity.ok(queriedProducts);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody CreateProduct createProduct) {
        log.info("Creating product");
        ProductResponse response = mediator.send(createProduct);
        log.info("Product was created");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable(value = "id", required = true) UUID id, @RequestBody UpdateProduct updateProduct) {
        updateProduct.setId(id);
        mediator.send(updateProduct);
        log.info(String.format("Product with id: %s was updated", id));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable("id") UUID id) {
        mediator.send(DeleteProductById.builder().id(id).build());
        log.info(String.format("Product with id: %S was deleted", id));
        return ResponseEntity.noContent().build();
    }
}
