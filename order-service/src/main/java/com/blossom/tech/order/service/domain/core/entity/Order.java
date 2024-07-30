package com.blossom.tech.order.service.domain.core.entity;

import com.blossom.tech.domain.entity.BaseEntity;
import com.blossom.tech.domain.valueobject.Money;
import com.blossom.tech.order.service.domain.core.exception.OrderDomainException;

import java.util.List;
import java.util.UUID;

public class Order extends BaseEntity<UUID> {

    private final UUID userId;
    private final Money totalPrice;
    private final List<OrderItem> orderItems;

    private Order(Builder builder) {
        super.setId(builder.id);
        userId = builder.userId;
        totalPrice = builder.totalPrice;
        orderItems = builder.orderItems;
    }

    public UUID getUserId() {
        return userId;
    }

    public Money getTotalPrice() {
        return totalPrice;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void initializeOrder() {
        setId(UUID.randomUUID());
        initializeOrderItems();
    }

    public void validateOrder() {
        validateInitialOrder();
        validateTotalPrice();
    }

    private void validateInitialOrder() {
        if (getId() != null) {
            throw new OrderDomainException("Order is not in correct state for initialization!");
        }
    }

    private void validateTotalPrice() {
        if (totalPrice == null || !totalPrice.isGreaterThanZero()) {
            throw new OrderDomainException("Total price must be greater than zero!");
        }
    }

    public void validateItemsPrice() {
        Money orderItemsTotal = orderItems.stream().map(orderItem -> {
            validateItemPrice(orderItem);
            return orderItem.getSubTotal();
        }).reduce(Money.ZERO, Money::add);

        if (!totalPrice.equals(orderItemsTotal)) {
            throw new OrderDomainException("Total price: " + totalPrice.getAmount()
                    + " is not equal to Order items total: " + orderItemsTotal.getAmount() + "!");
        }
    }

    private void validateItemPrice(OrderItem orderItem) {
        if (!orderItem.isValidPrice()) {
            throw new OrderDomainException("Order item price: " + orderItem.getPrice().getAmount() +
                    " is not valid for product " + orderItem.getOrderProduct().getId());
        }
    }

    private void initializeOrderItems() {
        long itemId = 1;
        for (OrderItem orderItem : orderItems) {
            orderItem.initializeOrderItem(super.getId(), itemId++);
        }
    }


    public static final class Builder {
        private UUID id;
        private UUID userId;
        private Money totalPrice;
        private List<OrderItem> orderItems;

        private Builder() {
        }

        public Builder id(UUID val) {
            id = val;
            return this;
        }

        public Builder userId(UUID val) {
            userId = val;
            return this;
        }

        public Builder totalPrice(Money val) {
            totalPrice = val;
            return this;
        }

        public Builder orderItems(List<OrderItem> val) {
            orderItems = val;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
