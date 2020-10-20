package com.project.projectCalendar;

import com.project.service.Holiday;
import com.project.service.HolidayRepository;
import com.project.service.ResourceController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
//@EnableAutoConfiguration
//If a controller in another package - we can indicate on our controller class
@ComponentScan(basePackageClasses = ResourceController.class)
@EnableMongoRepositories(basePackageClasses = HolidayRepository.class)
public class ProjectCalendarApplication  implements CommandLineRunner{

	@Autowired
	private HolidayRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(ProjectCalendarApplication.class, args);

	}


	@Override
	public void run(String... args) throws Exception {
		repository.save(new Holiday("Work", "7", "17", true, "Black", "1"));
		System.out.println(repository.findByTitle("Work"));
		System.out.println(repository.findByTitle("Travel"));

	}
}
