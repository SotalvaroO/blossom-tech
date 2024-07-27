package com.blossom.tech.product.service.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "tbl_categories")
public class CategoryEntity {
    @Id
    private UUID id;
    private String name;
    @ManyToMany(mappedBy = "categories")
    private List<ProductEntity> products;
}
