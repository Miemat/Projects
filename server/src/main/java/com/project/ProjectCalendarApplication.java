package com.project;

import com.project.service.ResourceController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProjectCalendarApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectCalendarApplication.class, args);
	}

}
