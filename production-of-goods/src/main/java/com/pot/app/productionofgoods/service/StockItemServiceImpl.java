package com.pot.app.productionofgoods.service;

import com.pot.app.productionofgoods.dto.StockItemDto;
import com.pot.app.productionofgoods.entity.Product;
import com.pot.app.productionofgoods.entity.StockItem;
import com.pot.app.productionofgoods.repository.jpa.StockItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.pot.app.productionofgoods.mapping.StockItemMapper.*;

@Service
@RequiredArgsConstructor
public class StockItemServiceImpl implements StockItemService {

    private final StockItemRepository repository;
    private final ProductService productService;

    @Override
    @Transactional
    public StockItemDto create(StockItemDto dto) {
        Product product = productService.findByName(dto.productName());
        StockItem stockItem = repository.save(toEntity(dto, product));
        return toDto(stockItem);
    }

    @Override
    @Transactional
    public List<StockItemDto> create(List<StockItemDto> dtos) {
        List<Product> products = productService.findAllByNameIn(getNames(dtos));
        List<StockItem> stockItems = repository.saveAll(toEntity(dtos, products));
        return toDto(stockItems);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<StockItem> findByNoTaskMinStock() {
        return repository.findFirstByNoTaskMinStock();
    }

    @Override
    @Transactional
    public void addQuantity(long productId, int quantity) {
        repository.addQuantity(productId, quantity);
    }

    @Override
    @Transactional
    public void addToCurrentQuantity(List<StockItem> items) {
        DataForUpdate dataForUpdate = getDataForUpdate(items, true);
        repository.updateStockItems(dataForUpdate.productIds(), dataForUpdate.quantities());
    }

    @Override
    public Set<Long> subtractFromCurrentQuantity(List<StockItem> items) {
        DataForUpdate dataForUpdate = getDataForUpdate(items, false);
        return repository.updateStockItems(dataForUpdate.productIds(), dataForUpdate.quantities());
    }
}
