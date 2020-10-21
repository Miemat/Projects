package com.project.service;

import com.project.model.Event;
import com.project.repository.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class ResourceController {

    @Autowired
    private EventRepository repository;

    @RequestMapping("/test")
    @ResponseBody
    public String testEvent() {

        Event event = new Event("title test", "2020.10.21", "2020.10.22", false, "black", "white");

        repository.save(event);

        log.info("Tessssssssssst");
        return "Test";
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
//    @PutMapping("/saveEvent")
    @RequestMapping("/saveEvent")
    @ResponseBody
    public String saveEvent(@RequestParam String title, @RequestParam String start, @RequestParam String end,
                         @RequestParam Boolean allDay, @RequestParam String colorPrimary, @RequestParam String colorSecondary,
                         @RequestParam String id) {

        Event event = new Event(title, start, end, allDay, colorPrimary, colorSecondary, id);
        log.info("Data Event: "+ event.toString());
        return "Done";
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
//    @DeleteMapping("/deleteEvent")
    @RequestMapping("/deleteEvent")
    @ResponseBody
    public String deleteEvent(@RequestParam String id) {

        log.info("Delete Event Id: "+ id);
        return "Done";
    }


}
