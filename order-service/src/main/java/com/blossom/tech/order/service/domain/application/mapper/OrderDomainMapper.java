package com.blossom.tech.order.service.domain.application.mapper;

import com.blossom.tech.order.service.domain.application.dto.command.CreateOrder;
import com.blossom.tech.order.service.domain.application.dto.response.CreateOrderResponse;
import com.blossom.tech.order.service.domain.application.dto.response.FindHistoryByUserIdResponse;
import com.blossom.tech.order.service.domain.core.entity.Order;

public interface OrderDomainMapper {
    Order createOrderToOrder(CreateOrder request);

    CreateOrderResponse orderToCreateOrderResponse(Order createdProduct);

    FindHistoryByUserIdResponse orderToFindHistoryByUserIdResponse(Order order);
}
