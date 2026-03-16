package com.pot.app.productionofgoods.integration.mayor.of.city.client;

import com.pot.app.core.dto.mayor.of.city.EmployeeDto;
import com.pot.app.core.dto.mayor.of.city.enums.EmployeeType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class MayorCityClient {

    private final WebClient webClient;

    public MayorCityClient(@Value("${integration.mayor-of-city.url}") String url) {
        this.webClient = WebClient.builder()
                .baseUrl(url)
                .build();
    }

    public EmployeeDto getEmployee(EmployeeType type) {
        return webClient.post()
                .uri("/v1/employee")
                .bodyValue(type)
                .retrieve()
                .bodyToMono(EmployeeDto.class)
                .block();
    }
}
