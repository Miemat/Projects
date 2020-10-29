package com.project.service;

import com.project.model.Event;
import com.project.repository.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
                            @RequestParam String allDay, @RequestParam String colorPrimary, @RequestParam String colorSecondary,
                            @RequestParam String id) {

        Event event = new Event(title, start, end, Boolean.parseBoolean(allDay), colorPrimary, colorSecondary);
        Event a = repository.save(event);
        log.info("Data Event: " + event.toString());
        return "Done tTest with Id: " + a.getId();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
//    @DeleteMapping("/deleteEvent")
    @RequestMapping("/deleteEvent")
    @ResponseBody
    public String deleteEvent(@RequestParam String id) {
        repository.deleteById(id);
        log.info("Delete Event Id: " + id);
        return "Done";
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping("/getAllEvents")
    @ResponseBody
    public List<Event> getAllEvents() {
        log.info("get all");
        List<Event> events = repository.findAll();
        try {
            events.forEach(data -> {
                log.info(data.toString());
            });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return events;
    }


}
