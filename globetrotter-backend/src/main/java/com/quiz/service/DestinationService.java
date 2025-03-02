package com.quiz.service;

import java.io.IOException;
import com.quiz.model.Destination;

/*
 * This service is for mantaining destinations related informations
 */
public interface DestinationService {
    
    Destination getRandomDestination() throws IOException;
}