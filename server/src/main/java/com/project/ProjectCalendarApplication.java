package com.project;

import com.project.service.ResourceController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
//@EnableAutoConfiguration
//If a controller in another package - we can indicate on our controller class
//@ComponentScan(basePackageClasses = ResourceController.class)
public class ProjectCalendarApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectCalendarApplication.class, args);
	}
}
