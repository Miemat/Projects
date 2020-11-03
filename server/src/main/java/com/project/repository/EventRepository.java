package com.project.repository;


import com.project.model.Event;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EventRepository extends MongoRepository<Event, String> {
    public Event findByTitle(String title);
    public static Query getAllEventsByDate(String startDate, String endDate){
        MongoOperations mongoOperations;
        Query query = new Query();
        return query.addCriteria(Criteria.where("start").gte(startDate).and("start").lt(endDate));
    }
}
