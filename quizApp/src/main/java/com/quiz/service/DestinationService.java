package com.quiz.service;

import java.io.IOException;

import com.quiz.model.Destination;

public interface DestinationService {
    Destination getRandomDestination() throws IOException;
}