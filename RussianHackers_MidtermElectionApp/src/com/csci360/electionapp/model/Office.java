package com.csci360.electionapp.model;

import java.util.ArrayList;

public class Office {
    public String nameOfOffice; public String getNameOfOffice(){return nameOfOffice;}
    public ArrayList<String> candidates; public ArrayList<String> getCandidates(){return candidates;}

    public Office(String name){
        this.nameOfOffice = name;
    }

    public void addCandidate(String candidate){
        candidates.add(candidate);
    }



}
