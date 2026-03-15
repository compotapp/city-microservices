package com.pot.app.productionofgoods.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderItemStatus {

    ACTIVE("активно"),
    CANCELLED("отменено"),
    OUT_OF_STOCK("нет в наличии"),
    RESERVED("зарезервировано");

    private final String title;
}
