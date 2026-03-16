package com.pot.app.productionofgoods.service.work;

import com.pot.app.productionofgoods.entity.Employee;
import com.pot.app.productionofgoods.entity.Task;
import com.pot.app.productionofgoods.service.EmployeeService;
import com.pot.app.productionofgoods.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.pot.app.core.dto.mayor.of.city.enums.EmployeeType.WORKER;
import static com.pot.app.productionofgoods.enums.TaskStatus.ACTIVE;
import static com.pot.app.productionofgoods.enums.TaskStatus.COMPLETE;
import static com.pot.app.productionofgoods.service.work.ExperienceProcessor.processExperienceGain;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductionManagerServiceImpl implements ProductionManagerService {

    private final EmployeeFallbackService employeeFallbackService;
    private final EmployeeService employeeService;
    private final ProductionWorkerService productionWorkerService;
    private final TaskService taskService;

    @Override
    public void work(Employee manager, Task task) {
        int oldCompleteQuantity = task.getCompleteQuantity();
        int newCompleteQuantity = task.getCompleteQuantity();
        int points = manager.getPoints();
        boolean taskComplete = false;
        while (points > 0 && !taskComplete) {
            Employee worker = employeeFallbackService.getEmployee(WORKER);
            task = productionWorkerService.work(worker, task);
            processExperienceGain(manager, 1);
            points = manager.getPoints();
            newCompleteQuantity = task.getCompleteQuantity();
            taskComplete = task.getQuantity() == newCompleteQuantity;
        }
        task.setStatus(taskComplete ? COMPLETE : ACTIVE);
        taskService.save(task);
        employeeService.save(manager);

        log.debug("{}: {} произвел {} из {} шт. продукта: {} задача: {} статус: {}",
                manager.getType().getTitle(),
                manager.getNumber(),
                newCompleteQuantity - oldCompleteQuantity,
                task.getQuantity() - oldCompleteQuantity,
                task.getProductId(),
                task.getNumber(),
                task.getStatus().getTitle());
    }
}
