package com.pot.app.mayorofcity.controller;

import com.pot.app.core.dto.mayor.of.city.EmployeeDto;
import com.pot.app.core.dto.mayor.of.city.enums.EmployeeType;
import com.pot.app.mayorofcity.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    @PostMapping
    public EmployeeDto findByType(@RequestBody EmployeeType type) {
        return service.findByType(type);
    }
}
