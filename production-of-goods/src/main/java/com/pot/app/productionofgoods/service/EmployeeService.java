package com.pot.app.productionofgoods.service;

import com.pot.app.core.dto.mayor.of.city.EmployeeDto;
import com.pot.app.core.dto.mayor.of.city.enums.EmployeeType;
import com.pot.app.productionofgoods.entity.Employee;

import java.util.Optional;

public interface EmployeeService {

    Employee save(Employee employee);

    EmployeeDto save(EmployeeDto dto);

    Optional<Employee> findFirstFreeByType(EmployeeType type);
}
