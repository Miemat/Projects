package com.project.repository;


import com.project.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Event, String> {
    public Event findByTitle(String title);

}
