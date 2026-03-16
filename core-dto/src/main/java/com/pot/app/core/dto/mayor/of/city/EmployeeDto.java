package com.pot.app.core.dto.mayor.of.city;

import com.pot.app.core.dto.mayor.of.city.enums.EmployeeType;

public record EmployeeDto(
        String number,
        Integer level,
        Integer exp,
        EmployeeType type
) {
}
