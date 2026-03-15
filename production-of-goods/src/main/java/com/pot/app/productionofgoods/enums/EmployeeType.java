package com.pot.app.productionofgoods.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmployeeType {

    DIRECTOR("директор"),
    MANAGER("менеджер"),
    WORKER("рабочий");

    private final String title;
}
