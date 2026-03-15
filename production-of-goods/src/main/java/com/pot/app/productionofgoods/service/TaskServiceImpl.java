package com.pot.app.productionofgoods.service;

import com.pot.app.productionofgoods.dto.TaskDto;
import com.pot.app.productionofgoods.entity.Task;
import com.pot.app.productionofgoods.integration.production.of.numbers.service.NumberGeneratorService;
import com.pot.app.productionofgoods.repository.jpa.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.pot.app.core.dto.production.of.numbers.NumberSequenceRequest.SequenceType.TASK;
import static com.pot.app.productionofgoods.enums.TaskStatus.ACTIVE;
import static com.pot.app.productionofgoods.mapping.TaskMapper.toDto;
import static com.pot.app.productionofgoods.mapping.TaskMapper.toEntity;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;
    private final NumberGeneratorService numberGenerator;

    @Override
    @Transactional
    public Task save(Task task) {
        return repository.save(task);
    }

    @Override
    @Transactional
    public TaskDto save(TaskDto dto) {
        String number = numberGenerator.generate(TASK);
        Task task = repository.save(toEntity(dto, number));
        return toDto(task);
    }

    @Override
    @Transactional
    public List<Task> saveAll(List<Task> tasks) {
        return repository.saveAll(tasks);
    }

    @Override
    @Transactional
    public Optional<Task> findByStatusActive() {
        return repository.findFirstByStatus(ACTIVE);
    }
}
