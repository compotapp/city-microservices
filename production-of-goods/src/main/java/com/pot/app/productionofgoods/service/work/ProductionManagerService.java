package com.pot.app.productionofgoods.service.work;

import com.pot.app.productionofgoods.entity.Employee;
import com.pot.app.productionofgoods.entity.Task;

public interface ProductionManagerService {

    void work(Employee manager, Task task);
}
