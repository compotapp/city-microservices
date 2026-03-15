package com.pot.app.productionofgoods.controller;

import com.pot.app.productionofgoods.dto.ProductDto;
import com.pot.app.productionofgoods.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping("/create")
    public ProductDto create(@RequestBody ProductDto dto) {
        return service.create(dto);
    }

    @PostMapping("/create-all")
    public List<ProductDto> create(@RequestBody List<ProductDto> dtos) {
        return service.create(dtos);
    }
}
