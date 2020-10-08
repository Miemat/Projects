package com.project.service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@Slf4j
public class ResourceController {

    private final AtomicLong counter = new AtomicLong();

    //@PutMapping("/")
    @RequestMapping("/test")
    @ResponseBody
    public Holiday evPojo(@RequestParam String title) {
        log.info("It's work "+title);
        return new Holiday("Travel", "7:00", "22:00", true, "black", String.valueOf(counter.incrementAndGet()));
    }
}
