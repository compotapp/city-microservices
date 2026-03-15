package com.pot.app.productionofnumbers.service;

import com.pot.app.core.dto.production.of.numbers.NumberSequenceRequest;

import java.util.List;

public interface NumberSequenceService {

    List<String> generate(NumberSequenceRequest request);
}
