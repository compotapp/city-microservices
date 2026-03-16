package com.pot.app.productionofnumbers.controller;

import com.pot.app.core.dto.production.of.numbers.NumberSequenceRequest;
import com.pot.app.productionofnumbers.service.NumberGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/generate")
@RequiredArgsConstructor
public class NumberSequenceController {

    private final NumberGeneratorService service;

    @PostMapping
    public List<String> generate(@RequestBody NumberSequenceRequest request) {
        return service.generate(request);
    }


}
