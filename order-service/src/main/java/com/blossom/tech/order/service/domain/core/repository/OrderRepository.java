package com.blossom.tech.order.service.domain.core.repository;

import com.blossom.tech.order.service.domain.core.entity.Order;

import java.util.List;
import java.util.UUID;

public interface OrderRepository {

    Order save(Order order);

    List<Order> findAllByUserId(UUID userId);

}
