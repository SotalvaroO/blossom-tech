package com.blossom.tech.product.service.domain.core.entity;

import com.blossom.tech.domain.entity.BaseEntity;

import java.util.UUID;

public class Category extends BaseEntity<UUID> {
    private final String name;

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
