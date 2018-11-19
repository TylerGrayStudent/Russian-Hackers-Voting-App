package com.csci360.electionapp.model;

import java.util.ArrayList;

public class Office {
    public String nameOfOffice; public String getNameOfOffice(){return nameOfOffice;}
    public ArrayList<String> candidates; public ArrayList<String> getCandidates(){return candidates;}

    public Office(String name){
        this.nameOfOffice = name;
        candidates = new ArrayList<String>();
    }

    public void addCandidate(String candidate){
        if(contains(candidate)){
            System.out.println("CANDIDATE ALREADY EXISTS");
            return;
        }
        candidates.add(candidate);
    }

    public void removeCandidate(String candidate){
        candidates.remove(candidate);
    }

    public boolean contains(String candidate){
        for(String name: candidates){
            if(name.equals(candidate)){
                return true;
            }
        }
        return false;
    }


}
