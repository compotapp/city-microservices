package com.pot.app.productionofgoods.controller;

import com.pot.app.productionofgoods.dto.StockItemDto;
import com.pot.app.productionofgoods.service.StockItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/stock-item")
@RequiredArgsConstructor
public class StockItemController {

    private final StockItemService service;

    @PostMapping("/create")
    public StockItemDto create(@RequestBody StockItemDto dto) {
        return service.create(dto);
    }

    @PostMapping("/create-all")
    public List<StockItemDto> create(@RequestBody List<StockItemDto> dtos) {
        return service.create(dtos);
    }
}
