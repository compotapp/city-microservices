package com.pot.app.productionofgoods.service;

import com.pot.app.productionofgoods.dto.OrderDto;
import com.pot.app.productionofgoods.dto.OrderDto.OrderItemDto;
import com.pot.app.productionofgoods.entity.Order;
import com.pot.app.productionofgoods.entity.OrderItem;
import com.pot.app.productionofgoods.entity.Product;
import com.pot.app.productionofgoods.enums.OrderItemStatus;
import com.pot.app.productionofgoods.enums.OrderStatus;
import com.pot.app.productionofgoods.integration.production.of.numbers.service.NumberGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static com.pot.app.core.dto.production.of.numbers.NumberSequenceRequest.SequenceType.ORDER;
import static com.pot.app.core.dto.production.of.numbers.NumberSequenceRequest.SequenceType.OWNER;
import static com.pot.app.productionofgoods.enums.OrderItemStatus.OUT_OF_STOCK;
import static com.pot.app.productionofgoods.enums.OrderStatus.*;
import static com.pot.app.productionofgoods.mapping.OrderItemMapper.getProductNames;
import static com.pot.app.productionofgoods.mapping.OrderItemMapper.toEntity;
import static com.pot.app.productionofgoods.mapping.OrderMapper.toDto;
import static com.pot.app.productionofgoods.mapping.StockItemMapper.toEntity;

@Service
@RequiredArgsConstructor
public class ProductReservationServiceImpl implements ProductReservationService {

    private final NumberGeneratorService numberGenerator;
    private final ProductService productService;
    private final StockItemService stockItemService;
    private final OrderService orderService;

    @Override
    @Transactional
    public OrderDto reservOrder(OrderDto dto) {
        Order order = createOrder(RESERVED);
        order.setItems(createOrderItems(dto.orderItemDtos(), OrderItemStatus.RESERVED, order));
        Set<Long> reservedProductIds = stockItemService.subtractFromCurrentQuantity(toEntity(order.getItems()));
        order = orderService.save(updateReservationStatus(order, reservedProductIds));
        return toDto(order);
    }

    @Override
    @Transactional
    public OrderDto canselReserveOrder(String orderNumber) {
        Order order = orderService.findByNumber(orderNumber);
        stockItemService.addToCurrentQuantity(toEntity(order.getItems()));
        order = orderService.save(updateCanceledStatus(order));
        return toDto(order);
    }


    @Override
    @Transactional
    public OrderDto confirmReserveOrder(String orderNumber) {
        Order order = orderService.findByNumber(orderNumber);
        order.setStatus(RESERVED);
        return toDto(orderService.save(order));
    }

    private Order createOrder(OrderStatus status) {
        String ownerNumber = numberGenerator.generate(OWNER);
        String orderNumber = numberGenerator.generate(ORDER);
        return Order.builder()
                .owner(ownerNumber)
                .number(orderNumber)
                .status(status)
                .build();
    }

    private List<OrderItem> createOrderItems(List<OrderItemDto> dtos, OrderItemStatus status, Order order) {
        List<Product> products = productService.findAllByNameIn(getProductNames(dtos));
        return toEntity(dtos, status, products, order);
    }

    private Order updateReservationStatus(Order order, Set<Long> reservedProductIds) {
        boolean allReserved = order.getItems().size() == reservedProductIds.size();
        if (!allReserved) {
            order.setStatus(PENDING);
            order.getItems().stream()
                    .filter(item -> !reservedProductIds.contains(item.getProduct().getId()))
                    .forEach(item -> item.setStatus(OUT_OF_STOCK));
        }
        return order;
    }

    private Order updateCanceledStatus(Order order) {
        order.setStatus(CANCELLED);
        order.getItems().stream()
                .filter(item -> item.getStatus() == OrderItemStatus.RESERVED)
                .forEach(item -> item.setStatus(OrderItemStatus.CANCELLED));
        return order;
    }
}
