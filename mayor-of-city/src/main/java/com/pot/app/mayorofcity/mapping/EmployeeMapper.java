package com.pot.app.mayorofcity.mapping;

import com.pot.app.core.dto.mayor.of.city.EmployeeDto;
import com.pot.app.mayorofcity.entity.Employee;

public class EmployeeMapper {

    public static EmployeeDto toDto(Employee employee) {
        return new EmployeeDto(
                employee.getNumber(),
                employee.getLevel(),
                employee.getExp(),
                employee.getType()
        );
    }
}
