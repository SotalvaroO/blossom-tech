package com.blossom.tech.order.service.domain.application.handler;

import com.blossom.tech.domain.mediator.RequestHandler;
import com.blossom.tech.order.service.domain.application.dto.query.FindHistoryByUserId;
import com.blossom.tech.order.service.domain.application.dto.response.FindHistoryByUserIdResponse;
import com.blossom.tech.order.service.domain.application.mapper.OrderDomainMapper;
import com.blossom.tech.order.service.domain.core.repository.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

public class FindHistoryByUserIdHandler implements RequestHandler<FindHistoryByUserId, List<FindHistoryByUserIdResponse>> {

    private final OrderRepository orderRepository;
    private final OrderDomainMapper orderDomainMapper;

    public FindHistoryByUserIdHandler(OrderRepository orderRepository, OrderDomainMapper orderDomainMapper) {
        this.orderRepository = orderRepository;
        this.orderDomainMapper = orderDomainMapper;
    }

    @Override
    public List<FindHistoryByUserIdResponse> handle(FindHistoryByUserId request) {
        return orderRepository.findAllByUserId(request.getUserId())
                .stream().
                map(orderDomainMapper::orderToFindHistoryByUserIdResponse)
                .collect(Collectors.toList());
    }
}
