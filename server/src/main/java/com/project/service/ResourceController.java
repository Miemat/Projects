package com.project.service;
import com.project.model.EventColor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@Slf4j
public class ResourceController {

    private final AtomicLong counter = new AtomicLong();


    @CrossOrigin(origins = "*", allowedHeaders = "*")
//    @PutMapping("/saveEvent")
    @RequestMapping("/saveEvent")
    @ResponseBody
    public String evPojo(@RequestParam String title, @RequestParam String start, @RequestParam String end,
                         @RequestParam Boolean allDay, @RequestParam String colorPrimary, @RequestParam String colorSecondary,
                         @RequestParam String id) {
        log.info("It's work "+title);
        log.info("Date event: "+ start);
        Holiday holiday = new Holiday(title, start, end, allDay, colorPrimary, colorSecondary, id);

        log.info("Data Event: "+ holiday.toString());


        return "Done";
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test(@RequestParam String title) {
        log.info("It's work test "+title);
        log.info("dfsds");
//        return new Holiday("Travel", "7:00", "22:00", true, "black", String.valueOf(counter.incrementAndGet()));
        return "test";
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/test2")
    @ResponseBody
    public void test2(@RequestParam String title) {
        log.info("It's work test ");
        log.info("dfsds");
//        return new Holiday("Travel", "7:00", "22:00", true, "black", String.valueOf(counter.incrementAndGet()));
//        return "test";
    }

}
