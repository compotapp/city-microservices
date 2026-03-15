package com.pot.app.productionofgoods.integration.dto;

import com.pot.app.productionofgoods.enums.EmployeeType;

public record EmployeeDto(
        String number,
        Integer level,
        Integer exp,
        EmployeeType type
) {
}
