package com.pot.app.productionofgoods.dto;

public record StockItemDto(
        String productName,
        String productNumber,
        Integer quantity,
        Integer minStock,
        Integer maxStock
) {
}
