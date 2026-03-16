package com.pot.app.productionofgoods.service.work;

import com.pot.app.core.dto.mayor.of.city.enums.EmployeeType;
import com.pot.app.productionofgoods.entity.Employee;


public interface EmployeeFallbackService {

    Employee getEmployee(EmployeeType type);
}
