package com.pot.app.productionofgoods.integration.production.of.numbers.service;

import com.pot.app.core.dto.production.of.numbers.NumberSequenceRequest;
import com.pot.app.core.dto.production.of.numbers.NumberSequenceRequest.SequenceType;
import com.pot.app.productionofgoods.integration.production.of.numbers.client.NumberGeneratorClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NumberGeneratorServiceImpl implements NumberGeneratorService {

    private final NumberGeneratorClient client;

    @Override
    public String generate(SequenceType type) {
        return generate(type, 1).get(0);
    }

    @Override
    public List<String> generate(SequenceType type, int count) {
        return client.generate(new NumberSequenceRequest(type, count));
    }
}
