package com.pot.app.productionofgoods.integration.mayor.of.city.service;

import com.pot.app.core.dto.mayor.of.city.EmployeeDto;
import com.pot.app.core.dto.mayor.of.city.enums.EmployeeType;
import com.pot.app.productionofgoods.integration.mayor.of.city.client.MayorCityClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MayorIntegrationServiceImpl implements MayorIntegrationService {

    private final MayorCityClient client;

    @Override
    public EmployeeDto getEmployee(EmployeeType type) {
        return client.getEmployee(type);
    }
}
