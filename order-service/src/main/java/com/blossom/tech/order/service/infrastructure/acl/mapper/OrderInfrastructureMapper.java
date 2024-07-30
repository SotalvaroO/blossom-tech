package com.blossom.tech.order.service.infrastructure.acl.mapper;

import com.blossom.tech.domain.valueobject.Money;
import com.blossom.tech.order.service.domain.core.entity.Order;
import com.blossom.tech.order.service.domain.core.entity.OrderItem;
import com.blossom.tech.order.service.infrastructure.entity.OrderEntity;
import com.blossom.tech.order.service.infrastructure.entity.OrderItemEntity;
import com.blossom.tech.product.service.infrastructure.acl.mapper.ProductInfrastructureMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

@Mapper
public interface OrderInfrastructureMapper {

    public OrderInfrastructureMapper INSTANCE = Mappers.getMapper(OrderInfrastructureMapper.class);


    @Mappings({
            @Mapping(source = "totalPrice.amount", target = "totalPrice"),
            @Mapping(source = "orderItems", target = "orderItems"),
    })
    OrderEntity toInfrastructureEntity(Order order);

    @Mappings({
            @Mapping(source = "totalPrice", target = "totalPrice.amount"),
            @Mapping(source = "orderItems", target = "orderItems")
    })
    Order toDomainEntity(OrderEntity orderEntity);

    @Mappings({
            @Mapping(source = "order.id", target = "orderId"),
            @Mapping(source = "price", target = "price.amount"),
            @Mapping(source = "subTotal", target = "subTotal.amount"),
    })
    OrderItem orderItemEntityToOrderItem(OrderItemEntity orderItemEntity);

    @Mappings({
            @Mapping(source = "orderId", target = "order.id"),
            @Mapping(source = "price.amount", target = "price"),
            @Mapping(source = "subTotal.amount", target = "subTotal"),
            @Mapping(source = "orderProduct.id", target = "productId")
    })
    OrderItemEntity orderItemToOrderItemEntity(OrderItem orderItem);

    @Named("moneyToBigDecimal")
    static BigDecimal moneyToBigDecimal(Money money) {
        return money != null ? money.getAmount() : null;
    }

    @Named("bigDecimalToMoney")
    static Money bigDecimalToMoney(BigDecimal bigDecimal) {
        return bigDecimal != null ? new Money(bigDecimal) : null;
    }

}
