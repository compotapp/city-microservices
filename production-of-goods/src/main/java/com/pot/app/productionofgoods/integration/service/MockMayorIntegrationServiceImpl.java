package com.pot.app.productionofgoods.integration.service;

import com.pot.app.productionofgoods.entity.Employee;
import com.pot.app.productionofgoods.enums.EmployeeType;
import com.pot.app.productionofgoods.integration.production.of.numbers.service.NumberGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.pot.app.core.dto.production.of.numbers.NumberSequenceRequest.SequenceType.EMPLOYEE;
import static com.pot.app.productionofgoods.enums.EmployeeStatus.FREE;

@Service
@RequiredArgsConstructor
public class MockMayorIntegrationServiceImpl implements MayorIntegrationService {

    private final NumberGeneratorService numberGenerator;

    @Override
    public Employee getEmployee(EmployeeType type) {
        return Employee.builder()
                .number(numberGenerator.generate(EMPLOYEE))
                .level(2)
                .exp(0)
                .points(2)
                .type(type)
                .status(FREE)
                .build();
    }
}
