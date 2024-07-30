package com.blossom.tech.order.service.domain.core;

import com.blossom.tech.order.service.domain.core.entity.Order;

import java.util.UUID;

public interface OrderDomainService {

    void initializeOrder(Order order, UUID userId);

}
