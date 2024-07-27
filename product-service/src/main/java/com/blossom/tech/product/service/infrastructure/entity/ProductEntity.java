package com.blossom.tech.product.service.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "tbl_products")
public class ProductEntity {
    @Id
    private UUID id;
    private String name;
    private String description;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "tbl_product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<CategoryEntity> categories;
    private BigDecimal price;
    private Long stock;
}
