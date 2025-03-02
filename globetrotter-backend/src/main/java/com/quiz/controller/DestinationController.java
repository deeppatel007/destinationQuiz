package com.quiz.controller;

import com.quiz.model.Destination;
import com.quiz.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import java.io.IOException;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/api/questions")
@CrossOrigin(origins = "*")
public class DestinationController {
    
    @Autowired
    private DestinationService destinationService;

    @GetMapping("/random")
    public Destination getRandomDestination() throws IOException {
        return destinationService.getRandomDestination();
    }
}