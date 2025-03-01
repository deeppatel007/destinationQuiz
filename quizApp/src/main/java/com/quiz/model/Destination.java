package com.quiz.model;

import java.util.List;

public class Destination {

    private String city;
    private String country;
    private List<String> clues;
    private List<String> fun_fact;
    private List<String> trivia;
    private List<String> options;
    // Constructors, Getters, and Setters
    public Destination() {
    }

    public Destination(String city, String country, List<String> clues, List<String> fun_fact, List<String> trivia, List<String> options) {
        this.city = city;
        this.country = country;
        this.clues = clues;
        this.fun_fact = fun_fact;
        this.trivia = trivia;
        this.options = options;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<String> getClues() {
        return clues;
    }

    public void setClues(List<String> clues) {
        this.clues = clues;
    }

    public List<String> getfun_fact() {
        return fun_fact;
    }

    public void setfun_fact(List<String> fun_fact) {
        this.fun_fact = fun_fact;
    }

    public List<String> getTrivia() {
        return trivia;
    }

    public void setTrivia(List<String> trivia) {
        this.trivia = trivia;
    }
    
    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}