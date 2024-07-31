package com.blossom.tech.order.service.infrastructure.acl.adapter;

import com.blossom.tech.order.service.domain.core.entity.Order;
import com.blossom.tech.order.service.domain.core.repository.OrderRepository;
import com.blossom.tech.order.service.infrastructure.acl.mapper.OrderInfrastructureMapper;
import com.blossom.tech.order.service.infrastructure.datasource.OrderDatasource;
import com.blossom.tech.order.service.infrastructure.entity.OrderEntity;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderRepositoryAdapter implements OrderRepository {

    private final OrderDatasource orderDatasource;
    private final OrderInfrastructureMapper orderInfrastructureMapper;

    public OrderRepositoryAdapter(OrderDatasource orderDatasource, OrderInfrastructureMapper orderInfrastructureMapper) {
        this.orderDatasource = orderDatasource;
        this.orderInfrastructureMapper = orderInfrastructureMapper;
    }

    @Override
    public Order save(Order order) {
        OrderEntity infrastructureEntity = orderInfrastructureMapper.toInfrastructureEntity(order);
        return orderInfrastructureMapper.toDomainEntity(orderDatasource.save(infrastructureEntity));
    }

    @Override
    public List<Order> findAllByUserId(UUID userId) {
        return orderDatasource.findAllByUserId(userId).stream().map(orderInfrastructureMapper::toDomainEntity).collect(Collectors.toList());
    }
}
