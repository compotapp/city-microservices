package com.pot.app.productionofgoods.integration.production.of.numbers.service;

import com.pot.app.core.dto.production.of.numbers.NumberSequenceRequest.SequenceType;

import java.util.List;

public interface NumberGeneratorService {
    String generate(SequenceType type);

    List<String> generate(SequenceType type, int count);
}
