package com.scienceforum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Science Forum Platform Backend Application Entry Point
 * 
 * This is the main application class that starts the Spring Boot application.
 * All components in com.scienceforum package will be automatically scanned and registered.
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.scienceforum")
public class ScienceForumApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScienceForumApplication.class, args);
    }
}
