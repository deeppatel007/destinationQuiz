package com.quiz.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.quiz.model.Destination;

@Repository
public interface DestinationRepository extends MongoRepository<Destination, String> {
    
}