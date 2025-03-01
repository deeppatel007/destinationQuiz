package com.quiz.service;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quiz.model.Destination;

@Service
public class DestinationServiceImpl implements DestinationService{
    
    @Value("classpath:destinations.json")
    private Resource destinationsResource;

    private List<Destination> destinations;
    private final Random random = new Random();
    public Destination getRandomDestination() throws IOException {
        if (destinations == null) {
            ObjectMapper objectMapper = new ObjectMapper();
            destinations = objectMapper.readValue(destinationsResource.getInputStream(), new TypeReference<List<Destination>>() {});
        }
        return destinations.get(random.nextInt(destinations.size()));
    }
}
