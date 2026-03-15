package com.pot.app.productionofgoods.integration.client;

import com.pot.app.core.dto.production.of.numbers.NumberSequenceRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class NumberGeneratorClient {

    private final WebClient webClient;

    public NumberGeneratorClient(@Value("${integration.production-of-numbers.url}") String url) {
        this.webClient = WebClient.builder()
                .baseUrl(url)
                .build();
    }

    public List<String> generate(NumberSequenceRequest request) {
        return webClient.post()
                .uri("/v1/generate")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<String>>() {})
                .block();
    }
}
