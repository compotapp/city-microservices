package com.pot.app.core.dto.mayor.of.city.enums;

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
