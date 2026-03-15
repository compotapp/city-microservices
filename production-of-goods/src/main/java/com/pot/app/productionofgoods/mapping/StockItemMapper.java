package com.pot.app.productionofgoods.mapping;

import com.pot.app.productionofgoods.dto.StockItemDto;
import com.pot.app.productionofgoods.entity.OrderItem;
import com.pot.app.productionofgoods.entity.Product;
import com.pot.app.productionofgoods.entity.StockItem;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.pot.app.productionofgoods.enums.OrderItemStatus.RESERVED;

public class StockItemMapper {

    public static StockItem toEntity(StockItemDto dto, Product product) {
        return StockItem.builder()
                .product(product)
                .quantity(dto.quantity())
                .minStock(dto.minStock())
                .maxStock(dto.maxStock())
                .build();
    }

    public static List<StockItem> toEntity(List<StockItemDto> dtos, List<Product> products) {
        Map<String, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getName, product -> product,
                        (existing, replacement) -> existing));//Опционально, если ключ уже есть в map можно выбрать оставить старое значение или добавить новое
        return dtos.stream()
                .map(dto -> toEntity(dto, productMap.get(dto.productName())))
                .toList();
    }

    public static StockItem toEntity(OrderItem orderItem) {
        return StockItem.builder()
                .product(orderItem.getProduct())
                .quantity(orderItem.getQuantity())
                .build();
    }

    public static List<StockItem> toEntity(List<OrderItem> orderItems) {
        return orderItems.stream()
                .filter(item -> item.getStatus() == RESERVED)
                .map(StockItemMapper::toEntity)
                .toList();
    }

    public static StockItemDto toDto(StockItem stockItem) {
        return new StockItemDto(
                stockItem.getProduct().getName(),
                stockItem.getProduct().getNumber(),
                stockItem.getQuantity(),
                stockItem.getMinStock(),
                stockItem.getMaxStock()
        );
    }

    public static List<StockItemDto> toDto(List<StockItem> stockItems) {
        return stockItems.stream()
                .map(StockItemMapper::toDto)
                .toList();
    }

    public static List<String> getNames(List<StockItemDto> dtos) {
        return dtos.stream()
                .map(StockItemDto::productName)
                .toList();
    }

    public static DataForUpdate getDataForUpdate(List<StockItem> items, boolean add) {
        int size = items.size();
        long[] productIds = new long[size];
        int[] quantities = new int[size];
        for (int i = 0; i < size; i++) {
            StockItem item = items.get(i);
            productIds[i] = item.getProduct().getId();
            quantities[i] = add ? item.getQuantity() : Math.negateExact(item.getQuantity());
        }
        return new DataForUpdate(productIds, quantities);
    }

    public record DataForUpdate(
            long[] productIds,
            int[] quantities
    ) {}
}
