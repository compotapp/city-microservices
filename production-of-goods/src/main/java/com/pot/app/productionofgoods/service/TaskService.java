package com.pot.app.productionofgoods.service;

import com.pot.app.productionofgoods.dto.TaskDto;
import com.pot.app.productionofgoods.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    Task save(Task task);

    TaskDto save(TaskDto dto);

    List<Task> saveAll(List<Task> tasks);

    Optional<Task> findByStatusActive();
}
