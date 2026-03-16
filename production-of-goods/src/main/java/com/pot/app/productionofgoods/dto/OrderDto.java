package com.pot.app.productionofgoods.dto;

import java.util.List;

public record OrderDto(
        String owner,
        String number,
        String status,
        List<OrderItemDto> orderItemDtos

) {

    public record OrderItemDto(
            String productName,
            String productNumber,
            int quantity,
            String status
    ) {}
}
