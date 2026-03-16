package com.pot.app.productionofgoods.service.work;

import com.pot.app.productionofgoods.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskDispatcherService {

    Optional<Task> createTaskMinStock();

    List<Task> createTaskProduction();

    Optional<Task> takeOnTask();
}
