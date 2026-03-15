package com.pot.app.productionofgoods.service.work;

import com.pot.app.productionofgoods.entity.Employee;
import com.pot.app.productionofgoods.entity.Task;
import com.pot.app.productionofgoods.service.EmployeeService;
import com.pot.app.productionofgoods.service.StockItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.pot.app.productionofgoods.service.work.ExperienceProcessor.processExperienceGain;

@Service
@RequiredArgsConstructor
public class ProductionWorkerServiceImpl implements ProductionWorkerService {

    private final EmployeeService employeeService;
    private final StockItemService stockItemService;

    @Override
    public Task work(Employee worker, Task task) {
        int completeQuantity = task.getQuantity() - task.getCompleteQuantity();
        int points = Math.min(worker.getPoints(), completeQuantity);
        task.setCompleteQuantity(task.getCompleteQuantity() + points);
        processExperienceGain(worker, points);
        stockItemService.addQuantity(task.getProductId(), points);
        employeeService.save(worker);
        return task;
    }
}
