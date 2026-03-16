package com.pot.app.productionofgoods.mapping.work;

import com.pot.app.productionofgoods.entity.OrderItem;
import com.pot.app.productionofgoods.entity.StockItem;
import com.pot.app.productionofgoods.entity.Task;

import java.util.List;
import java.util.stream.IntStream;

import static com.pot.app.productionofgoods.enums.TaskStatus.ACTIVE;
import static com.pot.app.productionofgoods.enums.TaskType.PRODUCTION;
import static com.pot.app.productionofgoods.enums.TaskType.PRODUCTION_MIN_STOCK;

public class DirectoryMapper {

    public static Task createTask(StockItem item, String number) {
        return Task.builder()
                .number(number)
                .productId(item.getProduct().getId())
                .quantity(item.getMinStock() - item.getQuantity())
                .completeQuantity(0)
                .type(PRODUCTION_MIN_STOCK)
                .status(ACTIVE)
                .build();
    }

    public static Task createTask(OrderItem item, String number) {
        return Task.builder()
                .number(number)
                .productId(item.getProduct().getId())
                .orderId(item.getOrder().getId())
                .quantity(item.getQuantity())
                .completeQuantity(0)
                .type(PRODUCTION)
                .status(ACTIVE)
                .build();
    }

    public static List<Task> createTask(List<OrderItem> orderItems, List<String> numbers) {
        return IntStream.range(0, numbers.size())
                .mapToObj(i -> createTask(orderItems.get(i), numbers.get(i)))
                .toList();
    }
}
