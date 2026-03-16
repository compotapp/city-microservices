package com.pot.app.core.dto.mayor.of.city.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmployeeStatus {

    FREE("свободный"),
    RELAX("отдыхает"),
    SEMI_FREE("полу-свободный"),
    WORK("работает");

    private final String title;
}
