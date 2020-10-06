package com.project.service;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class ResourceController {

    private final AtomicLong counter = new AtomicLong();

    //@PutMapping("/")
    @RequestMapping("/")
    public Holiday evPojo() {
        return new Holiday("Travel", "7:00", "22:00", true, "black", String.valueOf(counter.incrementAndGet()));
    }
}
