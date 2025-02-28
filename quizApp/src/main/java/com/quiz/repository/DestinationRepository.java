package com.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.quiz.model.Destination;

public interface DestinationRepository extends JpaRepository<Destination, Long> {
}