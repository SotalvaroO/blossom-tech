package com.blossom.tech.order.service.domain.application.dto.response;

import com.blossom.tech.domain.valueobject.Money;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Builder
public class FindHistoryByUserIdResponse {
    private final UUID orderId;
    private final Money totalPrice;
}
