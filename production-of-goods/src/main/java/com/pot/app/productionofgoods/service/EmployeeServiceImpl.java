package com.pot.app.productionofgoods.service;

import com.pot.app.productionofgoods.entity.Employee;
import com.pot.app.productionofgoods.enums.EmployeeType;
import com.pot.app.productionofgoods.integration.dto.EmployeeDto;
import com.pot.app.productionofgoods.repository.jpa.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.pot.app.productionofgoods.mapping.EmployeeMapping.toDto;
import static com.pot.app.productionofgoods.mapping.EmployeeMapping.toEntity;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    @Override
    @Transactional
    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    @Override
    @Transactional
    public EmployeeDto save(EmployeeDto dto) {
        Employee employee = repository.save(toEntity(dto));
        return toDto(employee);
    }

    @Override
    @Transactional
    public Optional<Employee> findFirstFreeByType(EmployeeType type) {
        return repository.findFirstFreeByType(type.name());
    }
}
