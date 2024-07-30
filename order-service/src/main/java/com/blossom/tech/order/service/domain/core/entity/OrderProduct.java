package com.blossom.tech.order.service.domain.core.entity;

import com.blossom.tech.domain.entity.BaseEntity;
import com.blossom.tech.domain.valueobject.Money;

import java.util.UUID;

public class OrderProduct extends BaseEntity<UUID> {
    private String name;
    private Money price;

    private OrderProduct(Builder builder) {
        super.setId(builder.id);
        name = builder.name;
        price = builder.price;
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static final class Builder {
        private UUID id;
        private String name;
        private Money price;

        private Builder() {
        }

        public Builder id(UUID val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public OrderProduct build() {
            return new OrderProduct(this);
        }
    }
}
