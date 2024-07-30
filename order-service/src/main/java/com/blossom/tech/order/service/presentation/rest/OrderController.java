package com.blossom.tech.order.service.presentation.rest;

import com.blossom.tech.domain.mediator.Mediator;
import com.blossom.tech.order.service.domain.application.dto.command.CreateOrder;
import com.blossom.tech.order.service.domain.application.dto.response.CreateOrderResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    private final Mediator mediator;

    public OrderController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrder createOrder){
        CreateOrderResponse response = mediator.send(createOrder);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
