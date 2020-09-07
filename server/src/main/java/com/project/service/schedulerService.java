package com.project.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class schedulerService {

    @Scheduled(fixedRate = 2000)
    public void reportCurrentTime() {
        log.info("testowy Test");
    }

}
