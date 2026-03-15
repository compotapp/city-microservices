package com.pot.app.productionofgoods.service.work;

import com.pot.app.productionofgoods.entity.Employee;
import com.pot.app.productionofgoods.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.pot.app.productionofgoods.enums.EmployeeType.DIRECTOR;
import static com.pot.app.productionofgoods.enums.EmployeeType.MANAGER;
import static com.pot.app.productionofgoods.service.work.ExperienceProcessor.processExperienceGain;

@Service
@Slf4j
@RequiredArgsConstructor
public class DirectorServiceImpl implements DirectorService {

    private final static int CASE_SIZE = 3;
    private final EmployeeFallbackService employeeFallbackService;
    private final TaskDispatcherService taskDispatcherService;
    private final ProductionManagerService productionManagerService;
    private final EmployeeService employeeService;
    private final ExecutorService executorService = Executors.newFixedThreadPool(3);

//    @Override
//    public void work() {
//        Employee director = employeeFallbackService.getEmployee(DIRECTOR);
//        int points = director.getPoints();
//        while (points > 0) {
//            Optional<Task> task = taskDispatcherService.takeOnTask();
//            if (task.isPresent()) {
//                Employee manager = employeeFallbackService.getEmployee(MANAGER);
//                Task workTask = task.get();
//                log.debug("директор: {} передал задачу в работу задачу: {}", director.getNumber(), workTask.getNumber());
//                executorService.execute(() -> productionManagerService.work(manager, workTask));
//                processExperienceGain(director, 1);
//            } else if (taskDispatcherService.createTaskMinStock().isPresent()) {
//                processExperienceGain(director, 1);
//            } else if (taskDispatcherService.createTaskProduction().isPresent()) {
//                processExperienceGain(director, 1);
//            } else {
//                processExperienceGain(director, 0);
//                log.debug("директор: {} нет задач", director.getNumber());
//            }
//            points = director.getPoints();
//        }
//        employeeService.save(director);
//    }

    @Override
    public void work() {
        Employee director = employeeFallbackService.getEmployee(DIRECTOR);
        int points = director.getPoints();
        while (points > 0) {
            for (int i = points; i < points + CASE_SIZE; i++) {
                if (taskCase(i % CASE_SIZE)) {
                    processExperienceGain(director, 1);
                    break;
                } else if (i + 1 == points + CASE_SIZE) {
                    processExperienceGain(director, 0);
                    log.debug("директор: {} нет задач", director.getNumber());
                }
            }
            points = director.getPoints();
        }
        employeeService.save(director);
    }

    private boolean taskCase(int caseNumber) {
        return switch (caseNumber) {
            case 0 -> taskDispatcherService.takeOnTask()
                    .map(task -> {
                        Employee manager = employeeFallbackService.getEmployee(MANAGER);
                        executorService.execute(() -> productionManagerService.work(manager, task));
                        log.debug("{}: {} взял в работу задачу: {}", manager.getType().getTitle(), manager.getNumber(), task.getNumber());
                        return task;
                    }).isPresent();
            case 1 -> taskDispatcherService.createTaskMinStock().isPresent();
            case 2 -> !taskDispatcherService.createTaskProduction().isEmpty();
            default -> false;
        };
    }
}
