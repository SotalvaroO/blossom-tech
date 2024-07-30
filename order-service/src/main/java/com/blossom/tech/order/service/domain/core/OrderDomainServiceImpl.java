package com.blossom.tech.order.service.domain.core;

import com.blossom.tech.order.service.domain.core.entity.Order;

import java.util.UUID;
import java.util.logging.Logger;

public class OrderDomainServiceImpl implements OrderDomainService {

    private final Logger log = Logger.getLogger(OrderDomainServiceImpl.class.getName());

    @Override
    public void initializeOrder(Order order, UUID userId) {
        order.validateOrder();
        order.initializeOrder();
        log.info(String.format("Order with id: %s is initiated", order.getId()));
    }
}
