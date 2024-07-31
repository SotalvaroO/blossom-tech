package com.blossom.tech.order.service.presentation.rest;

import com.blossom.tech.domain.mediator.Mediator;
import com.blossom.tech.order.service.domain.application.dto.command.CreateOrder;
import com.blossom.tech.order.service.domain.application.dto.query.FindHistoryByUserId;
import com.blossom.tech.order.service.domain.application.dto.response.CreateOrderResponse;
import com.blossom.tech.order.service.domain.application.dto.response.FindHistoryByUserIdResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    private final Mediator mediator;

    public OrderController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrder createOrder) {
        CreateOrderResponse response = mediator.send(createOrder);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<FindHistoryByUserIdResponse>> findUserHistory(@PathVariable("userId") UUID userId) {
        return ResponseEntity.ok(mediator.send(FindHistoryByUserId.builder().userId(userId).build()));
    }
}
