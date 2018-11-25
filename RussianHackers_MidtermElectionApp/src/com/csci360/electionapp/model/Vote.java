package com.csci360.electionapp.model;

public class Vote {
    private String candidate;
    private String office;

    public Vote(String candidate, String office){
        this.candidate = candidate;
        this.office = office;
    }

    public String toString(){
        return "Office: " + office + " | Candidate:  " + candidate;
    }


}
