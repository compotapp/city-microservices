package com.pot.app.productionofgoods.integration.mayor.of.city.service;


import com.pot.app.core.dto.mayor.of.city.EmployeeDto;
import com.pot.app.core.dto.mayor.of.city.enums.EmployeeType;

public interface MayorIntegrationService {

    EmployeeDto getEmployee(EmployeeType type);
}
