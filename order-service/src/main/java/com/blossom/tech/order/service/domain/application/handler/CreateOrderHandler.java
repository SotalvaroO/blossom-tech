package com.blossom.tech.order.service.domain.application.handler;

import com.blossom.tech.domain.mediator.RequestHandler;
import com.blossom.tech.order.service.domain.application.dto.command.CreateOrder;
import com.blossom.tech.order.service.domain.application.dto.response.CreateOrderResponse;
import com.blossom.tech.order.service.domain.application.mapper.OrderDomainMapper;
import com.blossom.tech.order.service.domain.core.entity.Order;
import com.blossom.tech.order.service.domain.core.entity.OrderItem;
import com.blossom.tech.order.service.domain.core.entity.OrderProduct;
import com.blossom.tech.order.service.domain.core.repository.OrderRepository;
import com.blossom.tech.product.service.domain.core.cosntant.ProductDomainConstant;
import com.blossom.tech.product.service.domain.core.entity.Product;
import com.blossom.tech.product.service.domain.core.exception.ProductNotFoundException;
import com.blossom.tech.product.service.domain.core.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateOrderHandler implements RequestHandler<CreateOrder, CreateOrderResponse> {

    private final OrderRepository orderRepository;
    private final OrderDomainMapper orderDomainMapper;
    private final ProductRepository productRepository;

    public CreateOrderHandler(OrderRepository orderRepository, OrderDomainMapper orderDomainMapper, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderDomainMapper = orderDomainMapper;
        this.productRepository = productRepository;
    }

    @Override
    //TODO: Make transactional
    public CreateOrderResponse handle(CreateOrder request) {
        Order order = orderDomainMapper.createOrderToOrder(request);
        order.validateOrder();
        order.initializeOrder();
        order.getOrderItems().forEach(orderItem -> {
            Product product = productRepository.findById(orderItem.getOrderProduct().getId())
                    .orElseThrow(() ->
                            new ProductNotFoundException(String.format(ProductDomainConstant.PRODUCT_NOT_FOUND, orderItem.getOrderProduct().getId()))
                    );
            orderItem.setProduct(Optional.of(product)
                    .map(orderProduct -> OrderProduct.builder()
                            .id(orderProduct.getId())
                            .name(orderProduct.getName())
                            .price(orderProduct.getPrice())
                            .build())
                    .get()
            );
            orderItem.calculateSubTotal();
            order.validateItemsPrice();
        });

        Order createdProduct = orderRepository.save(order);
        return orderDomainMapper.orderToCreateOrderResponse(createdProduct);
    }
}
