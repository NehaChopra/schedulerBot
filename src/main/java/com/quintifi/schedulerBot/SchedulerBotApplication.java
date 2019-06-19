package com.quintifi.schedulerBot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
/*
 * You can enable scheduling simply by adding the @EnableScheduling annotation
 * to the main application class or one of the Configuration classes.
 */
public class SchedulerBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchedulerBotApplication.class, args);
	}

}
