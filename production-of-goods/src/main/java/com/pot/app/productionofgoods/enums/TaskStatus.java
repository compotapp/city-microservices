package com.pot.app.productionofgoods.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TaskStatus {

    ACTIVE("активна"),
    COMPLETE("выполнена"),
    WORK("в работе");

    private final String title;
}
