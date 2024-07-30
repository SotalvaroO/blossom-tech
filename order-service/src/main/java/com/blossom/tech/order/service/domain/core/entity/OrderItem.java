package com.blossom.tech.order.service.domain.core.entity;

import com.blossom.tech.domain.entity.BaseEntity;
import com.blossom.tech.domain.valueobject.Money;

import java.util.UUID;

public class OrderItem extends BaseEntity<Long> {
    private UUID orderId;
    private OrderProduct orderProduct;
    private Integer quantity;
    private Money price;
    private Money subTotal;

    private OrderItem(Builder builder) {
        super.setId(builder.id);
        orderId = builder.orderId;
        orderProduct = builder.orderProduct;
        quantity = builder.quantity;
        price = builder.price;
        subTotal = builder.subTotal;
    }

    public void calculateSubTotal() {
        subTotal = orderProduct.getPrice().multiply(quantity);
    }

    public void setProduct(OrderProduct orderProduct) {
        this.orderProduct = orderProduct;
    }


    public static Builder builder() {
        return new Builder();
    }

    public UUID getOrderId() {
        return orderId;
    }

    public OrderProduct getOrderProduct() {
        return orderProduct;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Money getPrice() {
        return price;
    }

    public Money getSubTotal() {
        return subTotal;
    }

    void initializeOrderItem(UUID orderId, Long orderItemId) {
        this.orderId = orderId;
        super.setId(orderItemId);
    }

    boolean isValidPrice() {
        return price.isGreaterThanZero() &&
                price.equals(orderProduct.getPrice()) &&
                price.multiply(quantity).equals(subTotal);
    }

    public static final class Builder {
        private Long id;
        private UUID orderId;
        private OrderProduct orderProduct;
        private Integer quantity;
        private Money price;
        private Money subTotal;

        private Builder() {
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder orderId(UUID val) {
            orderId = val;
            return this;
        }

        public Builder product(OrderProduct val) {
            orderProduct = val;
            return this;
        }

        public Builder quantity(Integer val) {
            quantity = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder subTotal(Money val) {
            subTotal = val;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}
