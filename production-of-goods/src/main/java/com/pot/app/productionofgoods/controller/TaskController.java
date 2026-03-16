package com.pot.app.productionofgoods.controller;

import com.pot.app.productionofgoods.dto.TaskDto;
import com.pot.app.productionofgoods.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService service;

    @PostMapping("/create")
    public TaskDto create(@RequestBody TaskDto dto) {
        return service.save(dto);
    }
}
