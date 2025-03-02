package com.quiz.model;

import lombok.Data;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Users")
public class User {
    
    @Id
    private String username;
    private int correctAnswers;
    private int incorrectAnswers;
}