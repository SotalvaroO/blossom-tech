package com.blossom.tech.order.service.infrastructure.acl.mapper;

import com.blossom.tech.domain.valueobject.Money;
import com.blossom.tech.order.service.domain.application.dto.ProductItem;
import com.blossom.tech.order.service.domain.application.dto.command.CreateOrder;
import com.blossom.tech.order.service.domain.application.dto.response.CreateOrderResponse;
import com.blossom.tech.order.service.domain.application.dto.response.FindHistoryByUserIdResponse;
import com.blossom.tech.order.service.domain.application.mapper.OrderDomainMapper;
import com.blossom.tech.order.service.domain.core.entity.Order;
import com.blossom.tech.order.service.domain.core.entity.OrderItem;
import com.blossom.tech.order.service.domain.core.entity.OrderProduct;

import java.util.stream.Collectors;

public class OrderDomainMapperAdapter implements OrderDomainMapper {
    @Override
    public Order createOrderToOrder(CreateOrder request) {
        return Order.builder()
                .userId(request.getUserId())
                .totalPrice(new Money(request.getPrice()))
                .orderItems(
                        request.getItems().stream()
                                .map(this::productItemToOrderItem)
                                .collect(Collectors.toList())
                )
                .build();
    }

    @Override
    public CreateOrderResponse orderToCreateOrderResponse(Order createdProduct) {
        return null;
    }

    @Override
    public FindHistoryByUserIdResponse orderToFindHistoryByUserIdResponse(Order order) {
        return FindHistoryByUserIdResponse.builder()
                .orderId(order.getId())
                .totalPrice(order.getTotalPrice())
                .build();
    }

    private OrderItem productItemToOrderItem(ProductItem productItem) {
        return OrderItem.builder()
                .product(OrderProduct.builder().id(productItem.getProductId()).build())
                .quantity(productItem.getQuantity())
                .price(new Money(productItem.getPrice()))
                .build();
    }
}
