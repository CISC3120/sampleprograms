package edu.cuny.brooklyn.cisc3120.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
public class GpaAppMonoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GpaAppMonoApplication.class, args);
	}
}
