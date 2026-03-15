package com.pot.app.productionofgoods.controller;

import com.pot.app.productionofgoods.service.work.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/work")
@RequiredArgsConstructor
public class WorkController {

    private final DirectorService directorService;

    @GetMapping
    public void work() {
        directorService.work();
    }
}
