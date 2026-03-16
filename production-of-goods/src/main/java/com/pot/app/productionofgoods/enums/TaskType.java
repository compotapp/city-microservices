package com.pot.app.productionofgoods.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TaskType {

    PRODUCTION("производство"),
    PRODUCTION_MIN_STOCK("производство минимального кол-ва"),
    DELIVERY("доставка");

    private final String title;
}
