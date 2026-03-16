package com.pot.app.productionofgoods.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {

    CANCELLED("отменено"),
    COMPLETED("выполнено"),
    CONFIRMED("подтверждено"),
    PENDING("ожидает"),
    RESERVED("зарезервировано");

    private final String title;
}
