package com.pot.app.productionofgoods.mapping;

import com.pot.app.productionofgoods.dto.ProductDto;
import com.pot.app.productionofgoods.entity.Product;

import java.util.List;
import java.util.stream.IntStream;

public class ProductMapper {

    public static Product toEntity(ProductDto dto, String number) {
        return Product.builder()
                .number(number)
                .name(dto.name())
                .category(dto.category())
                .build();
    }

    public static List<Product> toEntity(List<ProductDto> dtos, List<String> numbers) {
        return IntStream.range(0, dtos.size())
                .mapToObj(i -> toEntity(dtos.get(i), numbers.get(i)))
                .toList();
    }

    public static ProductDto toDto(Product product) {
        return new ProductDto(
                product.getNumber(),
                product.getName(),
                product.getCategory());
    }

    public static List<ProductDto> toDto(List<Product> products) {
        return products.stream()
                .map(ProductMapper::toDto)
                .toList();
    }
}
