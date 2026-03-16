package com.pot.app.productionofgoods.service.work;

import com.pot.app.core.dto.mayor.of.city.enums.EmployeeType;
import com.pot.app.productionofgoods.entity.Employee;
import com.pot.app.productionofgoods.integration.mayor.of.city.service.MayorIntegrationService;
import com.pot.app.productionofgoods.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.pot.app.core.dto.mayor.of.city.enums.EmployeeStatus.RELAX;
import static com.pot.app.core.dto.mayor.of.city.enums.EmployeeStatus.WORK;
import static com.pot.app.productionofgoods.mapping.EmployeeMapping.toEntity;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeFallbackServiceImpl implements EmployeeFallbackService {

    private final EmployeeService employeeService;
    private final MayorIntegrationService mayorService;

    @Override
    @Transactional
    public Employee getEmployee(EmployeeType type) {
        log.debug("поиск свободного сотрудника тип: {}", type.getTitle());
        return employeeService.findFirstFreeByType(type)
                .map(empl -> {
                    log.debug("найден сотрудник номер: {}, тип: {}", empl.getNumber(), empl.getType().getTitle());
                    empl.setPoints(empl.getStatus() == RELAX ? empl.getLevel() : empl.getPoints());
                    empl.setStatus(WORK);
                    return employeeService.save(empl);
                })
                .orElseGet(() -> {
                    Employee employee = toEntity(mayorService.getEmployee(type));
                    log.debug("получен новый сотрудник номер: {}, тип: {}", employee.getNumber(), employee.getType().getTitle());
                    employee.setStatus(WORK);
                    return employeeService.save(employee);
                });
    }
}
