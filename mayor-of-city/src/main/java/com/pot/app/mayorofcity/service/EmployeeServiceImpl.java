package com.pot.app.mayorofcity.service;

import com.pot.app.core.dto.mayor.of.city.EmployeeDto;
import com.pot.app.core.dto.mayor.of.city.enums.EmployeeType;
import com.pot.app.mayorofcity.entity.Employee;
import com.pot.app.mayorofcity.integration.production.of.numbers.service.NumberGeneratorService;
import com.pot.app.mayorofcity.mapping.EmployeeMapper;
import com.pot.app.mayorofcity.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.pot.app.core.dto.mayor.of.city.enums.EmployeeStatus.WORK;
import static com.pot.app.core.dto.production.of.numbers.NumberSequenceRequest.SequenceType.EMPLOYEE;
import static com.pot.app.mayorofcity.mapping.EmployeeMapper.toDto;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    
    private final EmployeeRepository repository;
    private final NumberGeneratorService numberGenerator;

    @Override
    @Transactional
    public EmployeeDto findByType(EmployeeType type) {
        return repository.findFirstFreeByType(type.toString())
                .map(EmployeeMapper::toDto)
                .orElseGet(() -> toDto(create(type)));
    }

    private Employee create(EmployeeType type) {
        String number = numberGenerator.generate(EMPLOYEE);
        Employee employee = Employee.builder()
                .number(number)
                .level(2)
                .exp(0)
                .type(type)
                .status(WORK)
                .build();
        return repository.save(employee);
    }
}
