package com.project.service;


import org.springframework.data.mongodb.repository.MongoRepository;

public interface HolidayRepository extends MongoRepository<Holiday, String> {
    public Holiday findByTitle(String title);
    //public void createHoliday(Holiday holiday);

}
