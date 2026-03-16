package com.pot.app.productionofgoods.service;

import com.pot.app.productionofgoods.dto.ProductDto;
import com.pot.app.productionofgoods.entity.Product;

import java.util.List;

public interface ProductService {

    ProductDto create(ProductDto dto);

    List<ProductDto> create(List<ProductDto> dtos);

    Product findByName(String name);

    List<Product> findAllByNameIn(List<String> names);
}
