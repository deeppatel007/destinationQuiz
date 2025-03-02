package com.quiz.model;

import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "destinations")
@Data
public class Destination {
    
    private String city;
    private String country;
    private List<String> clues;
    private List<String> fun_fact;
    private List<String> trivia;
    private List<String> options;
}