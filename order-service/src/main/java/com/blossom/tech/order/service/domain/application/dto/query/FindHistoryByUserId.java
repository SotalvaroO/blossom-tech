package com.blossom.tech.order.service.domain.application.dto.query;

import com.blossom.tech.domain.mediator.Request;
import com.blossom.tech.order.service.domain.application.dto.response.FindHistoryByUserIdResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Builder
public class FindHistoryByUserId implements Request<List<FindHistoryByUserIdResponse>> {
    private final UUID userId;
}
