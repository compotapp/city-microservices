package com.pot.app.productionofgoods.mapping;

import com.pot.app.productionofgoods.entity.Employee;
import com.pot.app.productionofgoods.integration.dto.EmployeeDto;

import static com.pot.app.productionofgoods.enums.EmployeeStatus.FREE;

public class EmployeeMapping {

    public static Employee toEntity(EmployeeDto dto) {
        return Employee.builder()
                .number(dto.number())
                .level(dto.level())
                .exp(dto.exp())
                .points(dto.level())
                .type(dto.type())
                .status(FREE)
                .build();
    }

    public static EmployeeDto toDto(Employee employee) {
        return new EmployeeDto(
                employee.getNumber(),
                employee.getLevel(),
                employee.getExp(),
                employee.getType()
        );
    }
}
