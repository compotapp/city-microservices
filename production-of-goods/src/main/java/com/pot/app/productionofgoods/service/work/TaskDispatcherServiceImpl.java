package com.pot.app.productionofgoods.service.work;

import com.pot.app.productionofgoods.entity.OrderItem;
import com.pot.app.productionofgoods.entity.Task;
import com.pot.app.productionofgoods.integration.production.of.numbers.service.NumberGeneratorService;
import com.pot.app.productionofgoods.service.OrderService;
import com.pot.app.productionofgoods.service.StockItemService;
import com.pot.app.productionofgoods.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.pot.app.core.dto.production.of.numbers.NumberSequenceRequest.SequenceType.TASK;
import static com.pot.app.productionofgoods.enums.OrderItemStatus.RESERVED;
import static com.pot.app.productionofgoods.enums.TaskStatus.WORK;
import static com.pot.app.productionofgoods.mapping.work.DirectoryMapper.createTask;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskDispatcherServiceImpl implements TaskDispatcherService {

    private final NumberGeneratorService numberGenerator;
    private final StockItemService stockItemService;
    private final TaskService taskService;
    private final OrderService orderService;

    private static void accept(Task task) {
        log.debug("создана задача номер: {}, тип: {}", task.getNumber(), task.getType().getTitle());
    }

    @Override
    @Transactional
    public Optional<Task> createTaskMinStock() {
        return stockItemService.findByNoTaskMinStock()
                .map(stockItem -> {
                    Task task = taskService.save(createTask(stockItem, numberGenerator.generate(TASK)));
                    accept(task);
                    return task;
                });
    }

    @Override
    @Transactional
    public List<Task> createTaskProduction() {
        return orderService.findByNoTaskReserved()
                .map(order -> {
                    List<OrderItem> orderItems = order.getItems()
                            .stream().filter(item -> item.getStatus() == RESERVED)
                            .toList();
                    List<String> numbers = numberGenerator.generate(TASK, orderItems.size());
                    List<Task> tasks = taskService.saveAll(createTask(orderItems, numbers));
                    tasks.forEach(TaskDispatcherServiceImpl::accept);
                    return tasks;
                }).orElseGet(ArrayList::new);
    }

    @Override
    @Transactional
    public Optional<Task> takeOnTask() {
        return taskService.findByStatusActive()
                .map(task -> {
                    task.setStatus(WORK);
                    task = taskService.save(task);
                    log.debug("задача в работе номер:{}, тип: {}", task.getNumber(), task.getType().getTitle());
                    return task;
                });
    }
}
