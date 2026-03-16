package com.pot.app.productionofgoods.mapping;

import com.pot.app.productionofgoods.dto.OrderDto.OrderItemDto;
import com.pot.app.productionofgoods.entity.Order;
import com.pot.app.productionofgoods.entity.OrderItem;
import com.pot.app.productionofgoods.entity.Product;
import com.pot.app.productionofgoods.enums.OrderItemStatus;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderItemMapper {

    public static List<OrderItem> toEntity(List<OrderItemDto> dtos, OrderItemStatus status, List<Product> products, Order order) {
        Map<String, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getName, product -> product));
        return dtos.stream()
                .map(dto -> OrderItem.builder()
                        .order(order)
                        .product(productMap.get(dto.productName()))
                        .quantity(dto.quantity())
                        .status(status)
                        .build())
                .collect(Collectors.toUnmodifiableList());
    }

    public static List<OrderItemDto> toDto(List<OrderItem> items) {
        return items.stream()
                .map(item -> new OrderItemDto(
                        item.getProduct().getName(),
                        item.getProduct().getNumber(),
                        item.getQuantity(),
                        item.getStatus().getTitle()))
                .toList();
    }

    public static List<String> getProductNames(List<OrderItemDto> dtos) {
        return dtos.stream()
                .map(OrderItemDto::productName)
                .toList();
    }
}
