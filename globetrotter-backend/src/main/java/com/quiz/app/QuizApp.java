package com.quiz.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;  

@SpringBootApplication(scanBasePackages = "com.quizApp")
@ComponentScan(basePackages = {"com.quiz"})
@EnableMongoRepositories(basePackages = "com.quiz.repository")
public class QuizApp 
{
    public static void main( String[] args )
    {
        SpringApplication.run(QuizApp.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Allow CORS for all endpoints
                        .allowedOrigins("http://localhost:3000","https://destinationquiznew.onrender.com/") // Allowed origins
                        .allowedMethods("GET", "POST", "PUT", "DELETE"); // Allowed methods
            }
        };
    }
}
