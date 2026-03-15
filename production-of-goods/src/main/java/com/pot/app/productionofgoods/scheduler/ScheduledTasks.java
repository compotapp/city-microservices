package com.pot.app.productionofgoods.scheduler;

import com.pot.app.productionofgoods.service.work.DirectorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ScheduledTasks {

    private final DirectorService directorService;

    //@Scheduled(cron = "0 * * * * *")
    @Scheduled(fixedRate = 30000)
    public void work() {
        try {
            directorService.work();
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }
}
