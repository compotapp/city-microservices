package com.pot.app.mayorofcity.service;

import com.pot.app.core.dto.mayor.of.city.EmployeeDto;
import com.pot.app.core.dto.mayor.of.city.enums.EmployeeType;

public interface EmployeeService {

    EmployeeDto findByType(EmployeeType type);
}
