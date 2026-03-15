package com.pot.app.productionofgoods.integration.service;


import com.pot.app.productionofgoods.entity.Employee;
import com.pot.app.productionofgoods.enums.EmployeeType;

public interface MayorIntegrationService {

    Employee getEmployee(EmployeeType type);
}
