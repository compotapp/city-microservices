package com.pot.app.productionofgoods.service;

import com.pot.app.productionofgoods.dto.StockItemDto;
import com.pot.app.productionofgoods.entity.StockItem;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface StockItemService {

    StockItemDto create(StockItemDto dto);

    List<StockItemDto> create(List<StockItemDto> dtos);

    Optional<StockItem> findByNoTaskMinStock();

    void addQuantity(long productId, int quantity);

    void addToCurrentQuantity(List<StockItem> items);

    Set<Long> subtractFromCurrentQuantity(List<StockItem> items);
}
