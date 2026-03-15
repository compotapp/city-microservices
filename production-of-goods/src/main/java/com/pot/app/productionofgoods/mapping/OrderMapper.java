package com.pot.app.productionofgoods.mapping;

import com.pot.app.productionofgoods.dto.OrderDto;
import com.pot.app.productionofgoods.entity.Order;

public class OrderMapper {

    public static OrderDto toDto(Order order) {
        return new OrderDto(
                order.getOwner(),
                order.getNumber(),
                order.getStatus().getTitle(),
                OrderItemMapper.toDto(order.getItems())
        );
    }
}
