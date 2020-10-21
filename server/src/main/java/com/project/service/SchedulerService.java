package com.project.service;

import com.project.model.Event;
import com.project.repository.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@EnableMongoRepositories(basePackageClasses = EventRepository.class)
class SchedulerService {

    @Autowired
    private EventRepository repository;

    @Scheduled(fixedRate = 4000)
    public void reportCurrentTime() {
        repository.save(new Event("Work", "7", "17", true, "Black", "Red", "1"));
        System.out.println(repository.findByTitle("Work"));
        System.out.println(repository.findByTitle("Travel"));
        log.info("testowy Test");
    }

}
