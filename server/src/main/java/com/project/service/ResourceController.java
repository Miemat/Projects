package com.project.service;

import com.project.model.Event;
import com.project.repository.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ResourceController {

    @Autowired
    private EventRepository repository;
    private MongoOperations mongoOperations;

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
    @RequestMapping(name = "/saveEvent", method = RequestMethod.PUT)
    @ResponseBody
    public String saveEvent(@RequestParam String title, @RequestParam String start, @RequestParam String end,
                         @RequestParam Boolean allDay, @RequestParam String colorPrimary, @RequestParam String colorSecondary,
                         @RequestParam String id) {

        Event event = new Event(title, start, end, allDay, colorPrimary, colorSecondary);
        Event a = repository.save(event);
        log.info("Data Event: "+ event.toString());
        return "Done tTest with Id: "+a.getId();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
//    @DeleteMapping("/deleteEvent")
    @RequestMapping(name = "/deleteEvent", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteEvent(@RequestParam String id) {
        repository.deleteById(id);
        log.info("Delete Event Id: "+ id);
        return "Done";
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(name = "/getAllEvents", method = RequestMethod.GET)
    @ResponseBody
    public List<Event> getAllEvents() {
        log.info("get all");
        List<Event> events = repository.findAll();
        try {
            for (Event event: events) {
                log.info(event.toString());
            }
        }catch(Exception e){
            log.error(e.getMessage(), e);
        }
        log.info("test: "+ events.get(0).toString());
        String b = events.get(0).toString();
        return events;
    }

    @CrossOrigin(origins = "*", allowCredentials = "*")
    @RequestMapping(name = "/getAllEventsByDate", method = RequestMethod.GET)
    @ResponseBody
    public List<Event> getAllEventsByDate(String startDate, String endDate){

        Query query = new Query();
        query.addCriteria(Criteria.where("start").gte(startDate).and("start").lt(endDate));

        return mongoOperations.find(query, Event.class);
    }


}
