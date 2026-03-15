package com.pot.app.productionofgoods.service;

import com.pot.app.productionofgoods.entity.Employee;
import com.pot.app.productionofgoods.enums.EmployeeType;
import com.pot.app.productionofgoods.integration.dto.EmployeeDto;

import java.util.Optional;

public interface EmployeeService {

    Employee save(Employee employee);

    EmployeeDto save(EmployeeDto dto);

    Optional<Employee> findFirstFreeByType(EmployeeType type);
}
