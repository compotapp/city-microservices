package com.pot.app.productionofgoods.service.work;

import com.pot.app.productionofgoods.entity.Employee;
import com.pot.app.productionofgoods.enums.EmployeeType;

public interface EmployeeFallbackService {

    Employee getEmployee(EmployeeType type);
}
