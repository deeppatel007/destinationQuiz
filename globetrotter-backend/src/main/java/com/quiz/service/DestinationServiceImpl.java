package com.quiz.service;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quiz.model.Destination;
import com.quiz.repository.DestinationRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class DestinationServiceImpl implements DestinationService{
    
    @Value("classpath:destinations.json")
    private Resource destinationsResource;
    private List<Destination> destinations;
    private final Random random = new Random();
    private static final Logger logger = LoggerFactory.getLogger(DestinationServiceImpl.class);
    private final DestinationRepository destinationRepository;

    @Autowired
    public DestinationServiceImpl(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }
    
    @PostConstruct
    void init() throws IOException {
        destinations = destinationRepository.findAll();
        logger.info("Number of questions have been cached successfully = {}", destinations.size());
        logger.info("✅ DestinationService initialized successfully!");
    }
    
    public Destination getRandomDestination()  {
        logger.info("Returning next random question");
        return destinations.get(random.nextInt(destinations.size()));
    }
}
