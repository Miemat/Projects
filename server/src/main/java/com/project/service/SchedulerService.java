package com.project.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
class SchedulerService {

//    @Scheduled(fixedRate = 2000)
    public void reportCurrentTime() {
        log.info("testowy Test");
    }

}
